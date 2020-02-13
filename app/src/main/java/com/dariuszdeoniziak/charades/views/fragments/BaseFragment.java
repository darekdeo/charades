package com.dariuszdeoniziak.charades.views.fragments;

import android.os.Bundle;

import com.dariuszdeoniziak.charades.modules.FragmentModule;
import com.dariuszdeoniziak.charades.modules.MappersModule;
import com.dariuszdeoniziak.charades.views.ComponentsFacade;

import org.codejargon.feather.Feather;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;


public abstract class BaseFragment extends Fragment {

    @Inject ComponentsFacade componentsFacade;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Feather feather = Feather.with(new FragmentModule(this), new MappersModule());
        feather.injectFields(this);
    }
}
