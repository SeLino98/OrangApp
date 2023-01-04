package com.example.orangapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.orangapp.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    //그냥 로그인 버튼 눌렀을 때 강제종료되는 문제 해결해야 함.ㅅ
    ActivityLoginBinding activityLoginBinding;
    private DatabaseReference mDatabaseRef;
    private FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(activityLoginBinding.getRoot());

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();

        LoginButtonClickListener loginBtn = new LoginButtonClickListener();
        activityLoginBinding.btnLogin.setOnClickListener(loginBtn);
        RegisterButtonClickListener regBtn = new RegisterButtonClickListener();
        activityLoginBinding.btnRegister.setOnClickListener(regBtn);

    }
    class LoginButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            String userEmail = activityLoginBinding.etUserEmail.getText().toString();
            String userPwd = activityLoginBinding.etUserPwd.getText().toString();
            if(userEmail.equals(null)||userPwd.equals(null)){

                Toast.makeText(LoginActivity.this, "아이디나 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
            }
            else{
                mFirebaseAuth.signInWithEmailAndPassword(userEmail,userPwd).
                        addOnCompleteListener(LoginActivity.this,
                                new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                        else{
                                            Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }).addOnFailureListener(LoginActivity.this,
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(LoginActivity.this, "오류 : "+ e, Toast.LENGTH_SHORT).show();
                                    }
                                });
            }
        }
    }
    class RegisterButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityLoginBinding.etUserEmail.setText("");
        activityLoginBinding.etUserPwd.setText("");
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
        if(currentUser != null){
         //로그인된 경우
            Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}