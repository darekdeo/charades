package com.dariuszdeoniziak.charades.views.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dariuszdeoniziak.charades.App;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getInstance().injectFields(this);
    }
}
