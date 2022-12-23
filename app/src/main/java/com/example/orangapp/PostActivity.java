package com.example.orangapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.orangapp.databinding.ActivityPostBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PostActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseRef;
    private FirebaseAuth mFirebaseAuth;
    ActivityPostBinding activityPostBinding;
    ArrayList<Uri> uriList = new ArrayList<>();     // 이미지의 uri를 담을 ArrayList 객체

    //임의로 카테고리 지정 for Test.
    private String category = "1" ;
    //임의로 카테고리 지정
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPostBinding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(activityPostBinding.getRoot());

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();

        PostUploadButtonClickListener regClick = new PostUploadButtonClickListener();
        activityPostBinding.btnRegPost.setOnClickListener(regClick);
        UploadImageButtonClickListener imageClick = new UploadImageButtonClickListener();
        activityPostBinding.btnRegImage.setOnClickListener(imageClick);

    }
    class PostUploadButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();

            String content = activityPostBinding.etContent.getText().toString();
            String title = activityPostBinding.etTitle.getText().toString();

            PostTable postTable = new PostTable();
            postTable.setUserUid(firebaseUser.getUid());
            postTable.setContent(content);
            postTable.setTitle(title);
            mDatabaseRef.child("PostTable").child("Post_Category").child(category).setValue(content)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(PostActivity.this, "등록 성공", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(PostActivity.this, "등록 실패", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            finish();
        }
    }

    class UploadImageButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 2222);
        }
    }

    //이미지 등록 버튼 클릭 후 이미지 선택 후 돌아오는 메서드
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("resultCode : ",resultCode+"");
        Log.d("requestCode : ",requestCode+"" );
        //data : 이미지
        if(data == null){
            //이미지 선택안한 경우
            Toast.makeText(this, "이미지 선택을 하지 않았습니다.", Toast.LENGTH_SHORT).show();
        }
        else{ // data 값이 null 이 아닌 경우
            if(data.getClipData() == null){
                //이미지 한 장 선택한 경우
//                ClipData clipData = data.getClipData();
//                Uri imageUri = clipData.getItemAt(0).getUri();
                Uri imageUri = data.getData();
                uriList.add(imageUri);
            }else{//이미지 여러장 선택한 경우
                ClipData clipData = data.getClipData();
                if(clipData.getItemCount()>10){
                    //선택 이미지가 10장 이상인 경우
                }else{ //1~10장인 경우
                    for(int i = 0; i < clipData.getItemCount(); i++){
                        //선택된 이미지 uri들을 가져온다.
                        Uri imageUri = clipData.getItemAt(i).getUri();
                        try {
                            uriList.add(imageUri);
                        }catch (Exception e){
                            Log.e("ExceptionError","e : "+ e);
                        }
                    }

                    PostImageAdapter adapter1 = new PostImageAdapter(uriList, getApplicationContext());
                    activityPostBinding.imageRecyclerView.setAdapter(adapter1);
                    RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);
                    activityPostBinding.imageRecyclerView.setLayoutManager(layoutManager1);

//                    postImageAdapter = new PostImageAdapter(uriList, getApplicationContext());
//                    recyclerView.setAdapter(postImageAdapter);
//                    recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
                }
            }
        }
    }
}