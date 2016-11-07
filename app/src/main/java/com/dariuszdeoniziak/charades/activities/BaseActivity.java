package com.dariuszdeoniziak.charades.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import com.dariuszdeoniziak.charades.App;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @LayoutRes
    protected int layoutResId;

    @Inject
    protected SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getAnnotations();
        setContentView(layoutResId);
        ButterKnife.bind(this);

        ((App) getApplication()).getDbComponent().inject(this);
    }

    protected void getAnnotations() {
        Layout layout;
        if ((layout = getClass().getAnnotation(Layout.class)) != null) {
            layoutResId = layout.value();
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Layout {
        @LayoutRes int value() default 0;
    }
}
