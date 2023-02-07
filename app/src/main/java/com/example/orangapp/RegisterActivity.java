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
    private FirebaseAuth mAuth;
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
    public boolean sendAuthPhoneNum(String phoneNum){
        //String checkPhoneNum = "+82"+phoneNum;
        String checkPhoneNum = "+82"+phoneNum;
        Log.d("checkPhoneNum ", phoneNum);
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(checkPhoneNum)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)// Activity (for callback binding)
                        .setCallbacks(mCallback)
                        // OnVerificationStateChangedCallbacks
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

        // below method is used when
        // OTP is sent from Firebase
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            // when we receive the OTP it
            // contains a unique id which
            // we are storing in our string
            // which we have already created.
            // verificationId = s;
        }

        // this method is called when user
        // receive OTP from Firebase.
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {


            // below line is used for getting OTP code
            // which is sent in phone auth credentials.
            final String code = phoneAuthCredential.getSmsCode();
            Log.d("CODE", code+" ");
            // checking if the code
            // is null or not.
            if (code != null) {
                // if the code is not null then
                // we are setting that code to
                // our OTP edittext field.
                // edtOTP.setText(code);

                // after setting this code
                // to OTP edittext field we
                // are calling our verifycode method.
                // verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

        }
        // callback method is called on Phone auth provider.


    };

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