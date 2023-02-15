package com.example.orangapp.adapter;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.orangapp.*;
import com.google.android.gms.common.internal.Objects;

public class ViewPagerAdapter extends FragmentStateAdapter{

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 :
                Log.d("cASE0000!!!!!","");
                break;
            case 1 :
                Log.d("cASE!!!!!","");
                break;
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
