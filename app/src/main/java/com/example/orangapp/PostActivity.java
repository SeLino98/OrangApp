package com.example.orangapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.orangapp.databinding.ActivityPostBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.util.ArrayList;
import com.example.orangapp.model.FirebaseFunction;



public class PostActivity extends AppCompatActivity {


    //TESTTTT
    private Spinner spinner2;
    ArrayList<String> arrayList;
    ArrayAdapter<CharSequence> arrayAdapter;

    //이미지 저장 파이어스토어
    private FirebaseFunction DB;
    private FirebaseStorage mStorage;
    private StorageReference storageRef ;

    //리사이클뷰에 띄울 대표이미지 값 선정.
    private int mainImageNum = 1;
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
        //파이어베이스에 이미지 업로드 인스턴스와 참조생성
        mStorage = FirebaseStorage.getInstance();
        storageRef = mStorage.getReference();
        //파이어베이스 인증과 게시글 내용 등록 인스턴스 생성 .
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();

//        ArrayAdapter<CharSequence> sAdapter = ArrayAdapter.createFromResource(this, R.array.question, android.R.layout.simple_spinner_dropdown_item);
//        Spinner spinner = new Spinner(this);
//        spinner.setAdapter(sAdapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(PostActivity.this, "HIIHI", Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//            }
//        });
//        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
//                android.R.layout.simple_spinner_dropdown_item,
//                R.array.question);
        arrayAdapter = ArrayAdapter.createFromResource(this, R.array.question, android.R.layout.simple_spinner_dropdown_item);


        spinner2 = (Spinner)findViewById(R.id.txt_question_type);
        spinner2.setAdapter(arrayAdapter);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),spinner2.getSelectedItem() +"가 선택되었습니다.",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //클릭 이벤트
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

            //파이어베이스에 게시글 내용 업로드
//            DB.CreatePostTable(title,content,firebaseUser.getUid());

            PostTable postTable = new PostTable();
            postTable.setUserUid(firebaseUser.getUid());
            postTable.setContent(content);
            postTable.setTitle(title);
            mDatabaseRef.child("Post").child("Category").child(category).child(title).push().setValue(content)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){


                                Toast.makeText(PostActivity.this, "등록 성공", Toast.LENGTH_SHORT).show();

                                //이미지 저장.
                                for(int i = 0; i<uriList.size();i++) {

                                    Log.d("uriListSize:",""+uriList.size());

                                    StorageReference riversRef = storageRef.child("Aimages/"+category+"/"+i+"");
                                    UploadTask uploadTask = riversRef.putFile(uriList.get(i));

                                    uploadTask.addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            //실패했을 때
                                            Toast.makeText(PostActivity.this, "업로드 실패", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            //이미지 저장 성공
                                            postTable.setImageUrl(taskSnapshot.toString());
                                            Log.d("ImageUrl : ",""+taskSnapshot.toString());
                                            //메인이미지 폴더 생성. 메인에 리사이클뷰에 띄울 때 대표이미지로 활용할 예정
                                            if(mainImageNum == 1 ){
                                                mDatabaseRef.child("Post").child("Category").child(category).child(title).child("MainImage").push().setValue(postTable.getImageUrl());
                                            }
                                            mDatabaseRef.child("Post").child("Category").child(category).child(title).child("Image").push().setValue(postTable.getImageUrl());
                                            Toast.makeText(PostActivity.this, "업로드 성공", Toast.LENGTH_SHORT).show();
                                            mainImageNum ++;
                                        }
                                    });



                                }
                                //
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
        Log.d("resultCode : ",resultCode+"");  //성공했을 때 -1 리턴
        Log.d("requestCode : ",requestCode+"" ); //2222 리턴
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
                            Log.d("imageUri",":"+imageUri);
                        }catch (Exception e){
                            Log.e("ExceptionError","e : "+ e);
                        }
                    }
                    //xml 페이지에 이미지 업로드_어뎁터
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