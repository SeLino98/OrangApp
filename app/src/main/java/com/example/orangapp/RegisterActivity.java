package com.example.orangapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.orangapp.databinding.ActivityRegisterBinding;
import com.example.orangapp.model.UserModel;
import com.example.orangapp.function.Message;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding activityRegisterBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(activityRegisterBinding.getRoot());
        RegisterUserButtonClickListener click = new RegisterUserButtonClickListener();
        activityRegisterBinding.btnRegister.setOnClickListener(click);
    }
    class RegisterUserButtonClickListener implements View
            .OnClickListener{
        @Override
        public void onClick(View view) {
            ///인터넷 연결 안되어 있을때 실패 메세지(함수로 만들것)
            String userEmail = activityRegisterBinding.etUserEmail.getText().toString().trim();
            String userPwd = activityRegisterBinding.etUserPwd.getText().toString().trim();
            UserModel UserDB = new UserModel();
            Message message = new Message();

            UserDB.getUserData(userEmail,userPwd).
                    addOnCompleteListener(RegisterActivity.this,
                            task -> {
                                if(task.isSuccessful()){
                                    boolean b = Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", userEmail);
                                    UserDB.inputUserData();
                                    Toast.makeText(RegisterActivity.this, "회원가입성공", Toast.LENGTH_SHORT).show();
//                                    message.toastMessage(RegisterActivity.this,"회원가입성공");
                                    finish();
                                }else{
                                    Log.w("", "createUserWithEmail:failure", task.getException());
                                }
                          });
        }
    }

}