package com.example.orangapp.function;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.orangapp.databinding.ActivityPostBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.util.ArrayList;

public class FirebaseFunction {

    private FirebaseAuth mFirebaseAuth;
    private String imageUrl;
    private String title;
    private String content;
    private String userUid;
    DatabaseReference mDatabaseRef;


    public FirebaseFunction(){

        FirebaseStorage mStorage;
        StorageReference storageRef;

    }

//            postTable.setUserUid(firebaseUser.getUid());
//            postTable.setContent(content);
//            postTable.setTitle(title);
    public void CreatePostTable(String title,String content,String uid){
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseFunction firebaseFunction = new FirebaseFunction();
        this.title = title;
        this.content = content;
        this.userUid = uid;

        this.mDatabaseRef.child("Post").child("Category").child("1").child(title).push().setValue(content)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
    }

}
