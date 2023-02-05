package com.example.orangapp.model;

import android.util.Log;

import com.example.orangapp.UserAccount;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class UserModel {

    private final DatabaseReference mDatabaseRef;
    private final FirebaseAuth mFirebaseAuth;
    private String userEmail;
    private String userPwd;
    private final UserAccount userAccount;

    public FirebaseAuth getFirebaseAuth(){
        return this.mFirebaseAuth;
    }

    public UserModel(){
        FirebaseStorage mStorage;
        StorageReference storageRef;
        this.mFirebaseAuth= FirebaseAuth.getInstance();
        this.mDatabaseRef= FirebaseDatabase.getInstance().getReference();
        this.userAccount = new UserAccount();
    }

    public Task<AuthResult> getUserData(String userEmail, String userPwd){
        return mFirebaseAuth.createUserWithEmailAndPassword(userEmail,userPwd);
    }

    public boolean checkExistUserDB(String userEmail, String userPwd){
        userAccount.setUserPwd(userPwd);
        userAccount.setUserEmail(userEmail);
        return mFirebaseAuth.createUserWithEmailAndPassword(userEmail,userPwd).isSuccessful();
    }

    public boolean checkIsSuccessfulInputData(Task<AuthResult> task){
        return task.isSuccessful();
    }

    public void inputUserData(){
        // FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
//        UserAccount account = new UserAccount();
//        account.setUserEmail(userAccount.getUserEmail());
//        account.setUserPwd(userAccount.getUserPwd());
//        account.setUserUid(firebaseUser.getUid());
        userAccount.setUserUid(user.getUid());
        mDatabaseRef.child("UserAccount").child(userAccount.getUserUid()).setValue(userAccount);
    }




}
