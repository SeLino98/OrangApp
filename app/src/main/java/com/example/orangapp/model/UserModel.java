package com.example.orangapp.model;

import android.util.Log;

import com.example.orangapp.UserAccount;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class UserModel {

    DatabaseReference mDatabaseRef;
    private final FirebaseAuth mFirebaseAuth;
    private String userEmail;
    private String userPwd;
    private final UserAccount userAccount;

    public UserModel(){
        FirebaseStorage mStorage;
        StorageReference storageRef;
        mFirebaseAuth= FirebaseAuth.getInstance();
        this.userAccount = new UserAccount();
        Log.d("CCCCCC!#@$@#!$","");
    }

    public Task<AuthResult> getUserData(String userEmail, String userPwd){
        return mFirebaseAuth.createUserWithEmailAndPassword(userEmail,userPwd);
    }
    public boolean checkExistUserDB(String userEmail, String userPwd){
        userAccount.setUserPwd(userPwd);
        Log.d("what?",userPwd);
        userAccount.setUserEmail(userEmail);
        Log.d("what?",userEmail);


        return mFirebaseAuth.createUserWithEmailAndPassword(userEmail,userPwd).isSuccessful();
    }
    public void inputUserData(){
        FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
//        UserAccount account = new UserAccount();
//        account.setUserEmail(userAccount.getUserEmail());
//        account.setUserPwd(userAccount.getUserPwd());
//        account.setUserUid(firebaseUser.getUid());
        Log.d("what?",mFirebaseAuth.getCurrentUser().getUid()+"");
        mDatabaseRef.child("UserAccount").child(mFirebaseAuth.getCurrentUser().getUid()).setValue(userAccount);
    }

}
