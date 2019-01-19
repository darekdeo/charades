package com.dariuszdeoniziak.charades.utils;

import android.content.Context;
import android.widget.Toast;


public class AndroidStaticsWrapper {
    public void showToast(Context context, String text, int length) {
        Toast.makeText(context, text, length).show();
    }

    public void showToast(Context context, int resId, int length) {
        Toast.makeText(context, resId, length).show();
    }
}
