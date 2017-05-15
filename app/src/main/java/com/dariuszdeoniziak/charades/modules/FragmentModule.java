package com.dariuszdeoniziak.charades.modules;

import com.dariuszdeoniziak.charades.models.interactors.ModelInteractor;
import com.dariuszdeoniziak.charades.models.interactors.SugarOrmInteractor;
import com.dariuszdeoniziak.charades.presenters.CategoriesListPresenter;
import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;

import org.codejargon.feather.Provides;

import java.lang.ref.WeakReference;

import javax.inject.Singleton;

public class FragmentModule {

    private WeakReference<BaseFragment> fragmentRef;

    public FragmentModule(BaseFragment fragment) {
        this.fragmentRef = new WeakReference<>(fragment);
    }

    @Provides
    @Singleton
    public CategoriesListPresenter provideCategoryListPresenter() {
        return new CategoriesListPresenter(provideModelInteractor());
    }

    @Provides
    @Singleton
    public ModelInteractor provideModelInteractor() {
        return new SugarOrmInteractor(fragmentRef.get().getActivity());
    }
}
