package com.example.orangapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.orangapp.databinding.ActivityRegisterBinding;
import com.example.orangapp.model.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding activityRegisterBinding;
    private DatabaseReference mDatabaseRef;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(activityRegisterBinding.getRoot());
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
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
//            Log.d("userString",mFirebaseAuth.createUserWithEmailAndPassword(userEmail, userPwd).toString());
//            Log.d("userClass",mFirebaseAuth.createUserWithEmailAndPassword(userEmail, userPwd).getClass().toString());
//            Log.d("userResult",mFirebaseAuth.createUserWithEmailAndPassword(userEmail, userPwd).getResult().toString());
//            Log.d("successful!!!",UserDB.checkExistUserDB(userEmail,userPwd)+"");

//            UserDB.getUserData(userEmail,userPwd).
//                    addOnCompleteListener(RegisterActivity.this,
//                            task -> {
//                                if(task.isSuccessful()){
//                                    FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
//                                    UserAccount account = new UserAccount();
//                                    account.setUserEmail(firebaseUser.getEmail());
//                                    account.setUserPwd(userPwd);
//                                    account.setUserUid(firebaseUser.getUid());
//                                    mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);
//                                    Toast.makeText(RegisterActivity.this, "회원가입성공", Toast.LENGTH_SHORT).show();
//                                    finish();
//                                }else{
//                                    Log.w("", "createUserWithEmail:failure", task.getException());
//                                }
//                          });
            Boolean asd = UserDB.checkExistUserDB(userEmail,userPwd);
            Log.d("asdasdzzz",asd.toString());
            if(asd){
//                                    FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
//                                    UserAccount account = new UserAccount();
//                                    account.setUserEmail(firebaseUser.getEmail());
//                                    account.setUserPwd(userPwd);
//                                    account.setUserUid(firebaseUser.getUid());
//                                    mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                UserDB.inputUserData();
                                    Toast.makeText(RegisterActivity.this, "회원가입성공", Toast.LENGTH_SHORT).show();
                                    Log.d("HIHI","");
                                    finish();

            }


        }
    }

}