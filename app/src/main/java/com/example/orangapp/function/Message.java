package com.example.orangapp.function;

import android.widget.Toast;
import com.example.orangapp.RegisterActivity;

public class Message {
    public void toastMessage(RegisterActivity registerActivity,String text){
        Toast.makeText(registerActivity, text, Toast.LENGTH_SHORT).show();
    }
}
