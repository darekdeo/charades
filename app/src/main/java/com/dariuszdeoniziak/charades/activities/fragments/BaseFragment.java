package com.dariuszdeoniziak.charades.activities.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dariuszdeoniziak.charades.activities.Layout;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    public final String tag;

    @LayoutRes
    protected int layoutResId;

    public BaseFragment() {
        tag = this.getClass().getSimpleName();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getAnnotations();

        View view = inflater.inflate(layoutResId, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    protected void getAnnotations() {
        Layout layout;
        if ((layout = getClass().getAnnotation(Layout.class)) != null) {
            layoutResId = layout.value();
        }
    }
}
