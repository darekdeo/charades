package com.dariuszdeoniziak.charades.modules;

import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;

import org.codejargon.feather.Provides;

import javax.inject.Singleton;

public class FragmentModule {

    private BaseFragment fragment;

    public FragmentModule(BaseFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    public BaseFragment provideFragment() {
        return fragment;
    }
}
