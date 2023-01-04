package com.example.orangapp;

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

import com.example.orangapp.databinding.FragmentMainPostBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MainPostFragment extends Fragment {


    private List<PostTable> postTableList = new ArrayList<>();
    private List<String> uidList = new ArrayList<>();
    private StorageReference listRef ;
    private FirebaseStorage storage;
    private String Category;
    FragmentMainPostBinding fragmentMainPostBinding;


    public MainPostFragment() {
        // Required empty public constructor
    }

    //프래그먼트 실행 시 호출되는 메서드다.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//       Category = "1";
         listRef = storage.getReference().child("Aimages/1");

//       listRef.listAll()
//               .addOnSuccessListener(new OnSuccessListener<ListResult>() {
//                   @Override
//                   public void onSuccess(ListResult listResult) {
//                       for (StorageReference prefix : listResult.getPrefixes()) {
//                           Log.d("prefix",""+prefix);
//                           // All the prefixes under listRef.
//                           // You may call listAll() recursively on them.
//                       }
//                       for (StorageReference item : listResult.getItems()) {
//                           // All the items under listRef.
//                       }
//                   }
//               })
//               .addOnFailureListener(new OnFailureListener() {
//                   @Override
//                   public void onFailure(@NonNull Exception e) {
//                   }
//               });

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
        return fragmentMainPostBinding.getRoot();
    }

}