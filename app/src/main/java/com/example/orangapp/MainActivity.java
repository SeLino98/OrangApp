package com.example.orangapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.orangapp.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    private DatabaseReference mDatabaseRef;
    private FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        mFirebaseAuth = FirebaseAuth.getInstance();

        PostEditButtonClickListener click = new PostEditButtonClickListener();
        activityMainBinding.btnEditPost.setOnClickListener(click);
        LogoutButtonClickListener click1 = new LogoutButtonClickListener();
        activityMainBinding.btnLogout.setOnClickListener(click1);
        UserInfoButtonClickListener click2 = new UserInfoButtonClickListener();
        activityMainBinding.btnUserInfo.setOnClickListener(click2);

    }
    class UserInfoButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, InfoUserActivity.class);
            startActivity(intent);
        }
    }
    class PostEditButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this,PostActivity.class);
            startActivity(intent);
        }
    }
    class LogoutButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            mFirebaseAuth.signOut();
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            Toast.makeText(MainActivity.this, "로그아웃 성공!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
        activityMainBinding.tvUserStatus.setText(currentUser.getEmail()+" 님");
        if(currentUser == null){
            Log.e("Wrong","잘못된 접근입니다.");
            Toast.makeText(this, "잘못된 접근입니다. ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show();
        }
    }
}