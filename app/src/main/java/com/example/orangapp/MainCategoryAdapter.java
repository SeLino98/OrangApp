package com.example.orangapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.ViewHolderClass> {


    private List<PostTable> postTableList = new ArrayList<>();
    private List<String> uidList = new ArrayList<>();
    private FirebaseStorage storage;
    private Context context = null;
    private int Category = 1;

    public MainCategoryAdapter(){}

    public MainCategoryAdapter(List<PostTable> postTableList, List<String> uidList){
        this.postTableList = postTableList;
        this.uidList = uidList;
        storage = FirebaseStorage.getInstance();
    }


    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.main_category_row, parent,false);
        MainCategoryAdapter.ViewHolderClass vhc = new ViewHolderClass(view);
        return vhc;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {
        holder.rowTextView.setText(postTableList.get(position).getTitle());
        //holder.rowImageView.setImageResource(postTableList.get(position).getImageUrl();
        String url = postTableList.get(position).getImageUrl();
           Glide.with(context)
                .load(url)
                .into(holder.rowImageView);
    }

    @Override
    public int getItemCount() {
        return postTableList.size();
    }

    class ViewHolderClass extends RecyclerView.ViewHolder{

        //main_category_image_row정보담기
        ImageView rowImageView ;
        TextView rowTextView;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            rowTextView = itemView.findViewById(R.id.row_category_tv);
            rowImageView = itemView.findViewById(R.id.row_category_image);;
        }
    }

}



