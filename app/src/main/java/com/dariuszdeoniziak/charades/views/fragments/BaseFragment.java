package com.dariuszdeoniziak.charades.views.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dariuszdeoniziak.charades.modules.FragmentModule;
import com.dariuszdeoniziak.charades.utils.FontInjector;
import com.dariuszdeoniziak.charades.views.Layout;

import org.codejargon.feather.Feather;

import trikita.knork.Knork;

public abstract class BaseFragment extends Fragment {

    public final String TAG;

    @LayoutRes protected int layoutResId;

    Feather feather;

    public BaseFragment() {
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feather = Feather.with(new FragmentModule(this));
        feather.injectFields(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getAnnotations();

        View view = inflater.inflate(layoutResId, container, false);
        Knork.inject(view, this);
        FontInjector.inject(this);

        return view;
    }

    protected void getAnnotations() {
        Layout layout;
        if ((layout = getClass().getAnnotation(Layout.class)) != null) {
            layoutResId = layout.value();
        }
    }

    @Override
    public void onDestroyView() {
        Knork.reset(this);
        super.onDestroyView();
    }
}
