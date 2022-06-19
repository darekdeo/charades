package com.dariuszdeoniziak.charades.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.dariuszdeoniziak.charades.modules.FragmentModule;
import com.dariuszdeoniziak.charades.modules.MappersModule;

import org.codejargon.feather.Feather;


public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Feather feather = Feather.with(new FragmentModule(this), new MappersModule());
//        feather.injectFields(this);
    }
}
