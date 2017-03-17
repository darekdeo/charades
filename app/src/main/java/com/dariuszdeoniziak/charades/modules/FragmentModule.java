package com.dariuszdeoniziak.charades.modules;

import com.dariuszdeoniziak.charades.models.interactors.ModelInteractor;
import com.dariuszdeoniziak.charades.models.interactors.SugarOrmInteractor;
import com.dariuszdeoniziak.charades.presenters.CategoryListPresenter;
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
    public CategoryListPresenter provideCategoryListPresenter() {
        return new CategoryListPresenter(provideModelInteractor());
    }

    @Provides
    @Singleton
    public ModelInteractor provideModelInteractor() {
        return new SugarOrmInteractor(fragment.getActivity());
    }
}
