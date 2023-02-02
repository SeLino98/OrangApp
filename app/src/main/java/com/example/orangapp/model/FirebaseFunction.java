package com.example.orangapp.model;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseFunction {

    private String imageUrl;
    private String title;
    private String content;
    private String userUid;
    DatabaseReference mDatabaseRef;
    private FirebaseAuth mFirebaseAuth;


    public FirebaseFunction(){

        FirebaseStorage mStorage;
        StorageReference storageRef;

    }


}
