package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;
import com.dariuszdeoniziak.charades.views.fragments.CategoriesFragment;

import javax.inject.Inject;

public class CategoriesFragmentPresenter extends BasePresenter {

    private CategoriesFragment view;

    @Inject
    public CategoriesFragmentPresenter(BaseFragment fragment) {
        this.view = (CategoriesFragment) fragment;
    }

    @Override
    public void onSave() {

    }

    @Override
    public void onTakeView() {

    }

    @Override
    public void onDropView() {
        view = null;
    }
}
