package com.example.orangapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.orangapp.databinding.ActivityPostBinding;
import com.example.orangapp.databinding.PostImageRowBinding;

import java.util.ArrayList;

public class PostImageAdapter  extends RecyclerView.Adapter<PostImageAdapter.ViewHolderClass1>{ //2.

    private ArrayList<Uri> list = null;
    private Context context = null;
    //3. 생성자
    PostImageAdapter(ArrayList<Uri> list, Context context){
        this.list = list;
        this.context = context;
    }


    //2. ViewHolderClass1 클래스 생성
    @NonNull
    @Override
    public ViewHolderClass1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.post_image_row, parent,false);
        PostImageAdapter.ViewHolderClass1 vhc1 = new PostImageAdapter.ViewHolderClass1(view);
        return vhc1;
    }

    //3. ViewHolderClass1 데이터 세팅팅
   @Override
    public void onBindViewHolder(@NonNull ViewHolderClass1 holder, int position) {
        Uri image_uri = list.get(position);
       Glide.with(context)
               .load(image_uri)
               .into(holder.rowImageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderClass1 extends RecyclerView.ViewHolder{

        //post_image_row 에 있는 주소 값을 담기
        ImageView rowImageView; // 1.
        public ViewHolderClass1(View itemView) {
            super(itemView);
            rowImageView = itemView.findViewById(R.id.imageView2);
        }
    }

}
