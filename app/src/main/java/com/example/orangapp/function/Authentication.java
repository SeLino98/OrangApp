package com.example.orangapp.function;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.orangapp.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;

import java.util.concurrent.TimeUnit;

public class Authentication extends ViewModel {

    private String url = "https://www.example.com/finishSignUp?cartId=1234";
    private final FirebaseAuth mAuth;
    public Authentication(){
        this.mAuth = FirebaseAuth.getInstance();
       // mAuth.setLanguageCode("kr");
    }
//    public boolean sendAuthPhoneNum(String phoneNum){
//        //String checkPhoneNum = "+82"+phoneNum;
//        String checkPhoneNum = phoneNum;
//        Log.d("checkPhoneNum ", phoneNum);
//        PhoneAuthOptions options =
//                PhoneAuthOptions.newBuilder(mAuth)
//                        .setPhoneNumber(checkPhoneNum)       // Phone number to verify
//                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//                        .setActivity(RegisterActivity.this)// Activity (for callback binding)
//                        .setCallbacks(mCallback)
//                        // OnVerificationStateChangedCallbacks
//                        .build();
//
//        Log.d("PAGHODASF",""+options);
//        PhoneAuthProvider.verifyPhoneNumber(options);
//        return true;
//    }
//    // callback method is called on Phone auth provider.
//    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
//
//            // initializing our callbacks for on
//            // verification callback method.
//            mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//
//        // below method is used when
//        // OTP is sent from Firebase
//        @Override
//        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//            super.onCodeSent(s, forceResendingToken);
//            // when we receive the OTP it
//            // contains a unique id which
//            // we are storing in our string
//            // which we have already created.
//            // verificationId = s;
//        }
//
//        // this method is called when user
//        // receive OTP from Firebase.
//        @Override
//        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
//            // below line is used for getting OTP code
//            // which is sent in phone auth credentials.
//            final String code = phoneAuthCredential.getSmsCode();
//            Log.d("CODE", code+" ");
//            // checking if the code
//            // is null or not.
//            if (code != null) {
//                // if the code is not null then
//                // we are setting that code to
//                // our OTP edittext field.
//                // edtOTP.setText(code);
//
//                // after setting this code
//                // to OTP edittext field we
//                // are calling our verifycode method.
//                // verifyCode(code);
//            }
//        }
//
//        @Override
//        public void onVerificationFailed(@NonNull FirebaseException e) {
//
//        }
//        // callback method is called on Phone auth provider.
//
//
//    };
}





//
//
//        public boolean AuthEmail(String email){
//        Log.d("what is your mail?",""+email);
//        Log.d("emailFunctions1",""+mAuth);
//        ActionCodeSettings actionCodeSettings =
//                ActionCodeSettings.newBuilder()
//                        // URL you want to redirect back to. The domain (www.example.com) for this
//                        // URL must be whitelisted in the Firebase Console.
//                        .setUrl(url)
//                        // This must be true
//                        .setHandleCodeInApp(true)
//                        .setIOSBundleId("com.example.orangapp")
//                        .setAndroidPackageName(
//                                "com.example.orangapp",
//                                false, /* installIfNotAvailable */
//                                "12"    /* minimumVersion */)
//                        .build();
//        Log.d("what is actionCodeSettings?",""+actionCodeSettings);
//        mAuth.sendSignInLinkToEmail(email, actionCodeSettings)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Log.d("TTTTTAAAGGG", "Email sent.");
//                        }
//                    }
//                });


//        Log.d("actionCodeSettings11",actionCodeSettings.toString()+"");
//        auth.sendSignInLinkToEmail(email,actionCodeSettings).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                Log.d("unusendSignInLinkToEmailseseseed ", ""+task);
//            }
//        });
