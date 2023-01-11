package com.example.orangapp;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.orangapp.databinding.FragmentMainPostBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MainPostFragment extends Fragment {


    //

    private List<PostTable> postTableList = new ArrayList<>();
    private List<String> uidList = new ArrayList<>();

    private FirebaseStorage storage;
    private String Category;
    FragmentMainPostBinding fragmentMainPostBinding;
    private FirebaseDatabase firebaseDatabase;


    public MainPostFragment() {
        // Required empty public constructor
    }

    //프래그먼트 실행 시 호출되는 메서드다.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("dddddddddsd : ","Frag_onCreate");
        Log.d("Fragment1  : ","");
        firebaseDatabase = FirebaseDatabase.getInstance();
        Log.d("Fragment2 : ","");
        firebaseDatabase.getReference().child("Post").child("Category").child("1")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

              PostTable postTable = snapshot.getValue(PostTable.class);
                Log.d("dddddddddsd",postTable.getTitle());
                Log.d("dddddddddsd", postTable.getContent());
                Log.d("dddddddddsd", postTable.getImageUrl());
                Log.d("dddddddddsd", postTable.getUserUid());
//                Log.d("dsdsdsdds",""+postTableList.size());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("dsdsdsdsdsdsdsdsd 4:", "");

            }
        });
    }

    //2. 프래그먼트에서 보여줄 뷰를 만든다.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentMainPostBinding = FragmentMainPostBinding.inflate(inflater);
        MainCategoryAdapter adapter = new MainCategoryAdapter(postTableList,uidList);
        fragmentMainPostBinding.categoryRecycle.setAdapter(adapter);
        fragmentMainPostBinding.categoryRecycle.setLayoutManager(new GridLayoutManager(getActivity(),3));
        Log.d("dddddddddsd : ","2");
        return fragmentMainPostBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("dddddddddsd : ","");
    }
}