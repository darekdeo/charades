package com.dariuszdeoniziak.charades.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dariuszdeoniziak.charades.modules.FragmentModule;
import com.dariuszdeoniziak.charades.modules.MappersModule;
import com.dariuszdeoniziak.charades.views.ComponentsFacade;
import com.dariuszdeoniziak.charades.views.Layout;

import org.codejargon.feather.Feather;

import javax.inject.Inject;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import trikita.knork.Knork;


public abstract class BaseFragment extends Fragment {

    @LayoutRes private int layoutResId;

    @Inject ComponentsFacade componentsFacade;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Feather feather = Feather.with(new FragmentModule(this), new MappersModule());
        feather.injectFields(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getAnnotations();

        View view = inflater.inflate(layoutResId, container, false);
        Knork.inject(view, this);

        return view;
    }

    private void getAnnotations() {
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
