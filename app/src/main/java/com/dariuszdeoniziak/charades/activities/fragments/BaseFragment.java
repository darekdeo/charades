package com.dariuszdeoniziak.charades.activities.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dariuszdeoniziak.charades.activities.Layout;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    public final String TAG;

    @LayoutRes protected int layoutResId;

    private Unbinder unbinder;

    public BaseFragment() {
        TAG = this.getClass().getSimpleName();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getAnnotations();

        View view = inflater.inflate(layoutResId, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    protected void getAnnotations() {
        Layout layout;
        if ((layout = getClass().getAnnotation(Layout.class)) != null) {
            layoutResId = layout.value();
        }
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
