package com.example.orangapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.orangapp.databinding.ActivityTestMainBinding;
import com.example.orangapp.databinding.ContentMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class TestMainActivity extends AppCompatActivity {
    private DatabaseReference mDatabaseRef;
    private FirebaseAuth mFirebaseAuth;
    private List<PostTable> postTableList = new ArrayList<>();
    private List<String> uidList = new ArrayList<>();
    private FirebaseStorage storage;

    ActivityTestMainBinding activityTestMainBinding;
    ContentMainBinding contentMainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTestMainBinding = ActivityTestMainBinding.inflate(getLayoutInflater());
        setContentView(activityTestMainBinding.getRoot());


    mFirebaseAuth = FirebaseAuth.getInstance();


    //프래그 먼트 생성->안에 리사이클 뷰 띄우기
//        MainPostFragment mainPostFragment = new MainPostFragment();
//        Log.d("dddddddddsd : ","Commit1");
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        Log.d("dddddddddsd : ","Commit2");
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.fragment,mainPostFragment);
//        fragmentTransaction.commit();//프래그먼트 적용<-
//        Log.d("dddddddddsd", "start");
//        Log.d("dddddddddsd", "end");

    //이벤트 클릭 리스너
    PostEditButtonClickListener click = new PostEditButtonClickListener();
        activityTestMainBinding.btnEditPost.setOnClickListener(click);
    LogoutButtonClickListener click1 = new LogoutButtonClickListener();
        activityTestMainBinding.btnLogout.setOnClickListener(click1);
   UserInfoButtonClickListener click2 = new UserInfoButtonClickListener();
        activityTestMainBinding.btnUserInfo.setOnClickListener(click2);


}

    @Override //액션바를 가져와서 화면 메뉴에 띄운다.
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        //main_menu 커스텀 바를 액션바 메뉴로 띄우는 함수
        return true;
    }

    //옵션버튼 클릭 됐을 때
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.search_bar:
                break;
            case R.id.category_1:
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.category_2:
                break;
            case R.id.category_3:
                break;
            case R.id.category_4:
                Toast.makeText(this, "Test/!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.goto_option:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

class UserInfoButtonClickListener implements View.OnClickListener{
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(TestMainActivity.this, InfoUserActivity.class);
        startActivity(intent);
    }
}
class PostEditButtonClickListener implements View.OnClickListener{
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(TestMainActivity.this,PostActivity.class);
        startActivity(intent);
    }
}
class LogoutButtonClickListener implements View.OnClickListener{
    @Override
    public void onClick(View view) {
        mFirebaseAuth.signOut();
        Intent intent = new Intent(TestMainActivity.this,LoginActivity.class);
        startActivity(intent);
        Toast.makeText(TestMainActivity.this, "로그아웃 성공!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
        activityTestMainBinding.tvUserStatus.setText(currentUser.getEmail()+" 님");
        if(currentUser == null){
            Log.e("Wrong","잘못된 접근입니다.");
            Toast.makeText(this, "잘못된 접근입니다. ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(TestMainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "메인페이지!", Toast.LENGTH_SHORT).show();
        }
    }

}
