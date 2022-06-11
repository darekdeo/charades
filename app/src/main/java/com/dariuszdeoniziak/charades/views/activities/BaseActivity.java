package com.dariuszdeoniziak.charades.views.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dariuszdeoniziak.charades.modules.ActivityModule;
import com.dariuszdeoniziak.charades.modules.MappersModule;

import org.codejargon.feather.Feather;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Feather feather = Feather.with(new ActivityModule(this), new MappersModule());
        feather.injectFields(this);
    }
}
