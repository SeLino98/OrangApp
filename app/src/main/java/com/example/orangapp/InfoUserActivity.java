package com.example.orangapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.orangapp.databinding.ActivityInfoUserBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InfoUserActivity extends AppCompatActivity {

    ActivityInfoUserBinding activityInfoUserBinding;
    private DatabaseReference mDatabaseRef;
    private FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInfoUserBinding = ActivityInfoUserBinding.inflate(getLayoutInflater());
        setContentView(activityInfoUserBinding.getRoot());

        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();

        mDatabaseRef.child("UserAccount").child(currentUser.getUid()).child("userPwd").
                get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()){
                        activityInfoUserBinding.textView9.setText(""+task.getResult().getValue());
                        }else{
                            Toast.makeText(InfoUserActivity.this, "실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        mDatabaseRef.child("UserAccount").child(currentUser.getUid()).child("userUid").
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        activityInfoUserBinding.textView10.setText(""+snapshot.getValue());
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });mDatabaseRef.child("UserAccount").child(currentUser.getUid()).child("userUid").
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        activityInfoUserBinding.textView11.setText(""+snapshot.getValue());
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
//        mDatabaseRef.child("UserCurrent").child(currentUser.getUid()).child("userEmail").
//                get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if(task.isSuccessful()){
//                    activityInfoUserBinding.textView9.setText(""+task.getResult().getValue());
//                }else{
//                    Toast.makeText(InfoUserActivity.this, "실패", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        activityInfoUserBinding.textView7.setText(currentUser.getEmail());
        activityInfoUserBinding.textView8.setText(currentUser.getUid());
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
        if(currentUser == null){
            Log.e("Wrong","잘못된 접근입니다.");
            Toast.makeText(this, "잘못된 접근입니다. ", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}