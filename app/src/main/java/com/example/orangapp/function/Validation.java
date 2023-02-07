package com.example.orangapp.function;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;

public class Validation {
<<<<<<< HEAD




=======
    public static boolean checkEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    public static boolean checkPhoneNumber(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.PHONE.matcher(target).matches());
    }
    public static boolean checkPassword(CharSequence target) {
        Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
        return !TextUtils.isEmpty(target) && PASSWORD_PATTERN.matcher(target).matches();
    }
>>>>>>> 5debf4f78840e22dbb49dc359f160eba70d40db5
}
