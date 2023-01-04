package com.example.orangapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.orangapp.databinding.ActivityOptionBinding;

public class OptionActivity extends AppCompatActivity {
    ActivityOptionBinding activityOptionBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOptionBinding = ActivityOptionBinding.inflate(getLayoutInflater());
        Toast.makeText(this, "option menu", Toast.LENGTH_SHORT).show();
        setContentView(activityOptionBinding.getRoot());
    }
}