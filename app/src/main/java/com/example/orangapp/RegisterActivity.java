package com.example.orangapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.orangapp.databinding.ActivityRegisterBinding;
import com.example.orangapp.function.Authentication;
import com.example.orangapp.model.UserModel;
import com.example.orangapp.function.Message;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding activityRegisterBinding;
    private boolean checkPhone = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(activityRegisterBinding.getRoot());
        RegisterUserButtonClickListener click = new RegisterUserButtonClickListener();
        activityRegisterBinding.btnRegister.setOnClickListener(click);
        EmailAuthButtonClickListener emailBtn = new EmailAuthButtonClickListener();
        activityRegisterBinding.btnEmailCheck.setOnClickListener(emailBtn);
        SmsAuthButtonClickListener smsBtn = new SmsAuthButtonClickListener();
        activityRegisterBinding.btnPhoneCheck.setOnClickListener(smsBtn);
    }
    class EmailAuthButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Authentication  auth = new Authentication();
            //if(valid is true){}
            String email =  activityRegisterBinding.etUserEmail.getText().toString().trim();
//            Log.d("EmailLog2",""+auth.AuthEmail(email));
//            auth.AuthEmail(email);
        }
    }
    class SmsAuthButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            String phoneNum = activityRegisterBinding.etUserPhone.getText().toString().trim();
            //if(valid is true){}
            sendAuthPhoneNum(phoneNum);
        }
    }
    //????????? RegisterActivity??? ?????? ?????????.
    public boolean sendAuthPhoneNum(String phoneNum){
        String checkPhoneNum = "+82"+phoneNum;
        Log.d("checkPhoneNum ", phoneNum);
        UserModel UserDB = new UserModel();
        Log.d("GETFIREBASAUTH",""+UserDB.getmFirebaseAuth().getCurrentUser().toString());
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(UserDB.getmFirebaseAuth())  //mAuth??? null?????? ?????? ,,,
                        .setPhoneNumber(checkPhoneNum)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)
                        .setCallbacks(mCallback)
                        .build();
        Log.d("PAGHODASF",""+options);
        PhoneAuthProvider.verifyPhoneNumber(options);
        return true;
    }
    // callback method is called on Phone auth provider.
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            // initializing our callbacks for on
            // verification callback method.
            mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        //1. ??????????????? ?????? ?????????, ??????????????? ???????????? ?????? ??????
        //2. ?????? ?????? UI???????????? ???.

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            String verificationId = s;
            Log.d("verfies",""+s);
            if(verificationId!=null){
                activityRegisterBinding.btnPhoneCheck.setText("????????????");
                activityRegisterBinding.btnPhoneCheck.setEnabled(false);
                checkPhone = true;
            }
        }
        //????????? ??????????????? ??????????????? ?????? ???????????? ????????? ??? ?????? ????????? ??? ??????
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            final String code = phoneAuthCredential.getSmsCode();
            Log.d("CODEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE", code+" ");
            if (code != null) {
               activityRegisterBinding.etUserCheckPhoneNum.setText(code);
            }
        }
        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Log.d("FIREBASEException : e",""+e);
        }
        @Override
        public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
            super.onCodeAutoRetrievalTimeOut(s);
        }
    };

    class RegisterUserButtonClickListener implements View
            .OnClickListener{
        @Override
        public void onClick(View view) {
            ///????????? ?????? ????????? ????????? ?????? ?????????(????????? ?????????)
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
                                    Toast.makeText(RegisterActivity.this, "??????????????????", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{
                                    Log.w("", "createUserWithEmail:failure", task.getException());
                                }
                          });
        }
    }
}