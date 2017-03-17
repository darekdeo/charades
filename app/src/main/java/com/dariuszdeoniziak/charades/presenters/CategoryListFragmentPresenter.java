package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.views.CategoryListView;
import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;
import com.dariuszdeoniziak.charades.views.fragments.CategoryListFragment;

import javax.inject.Inject;

import static android.R.attr.fragment;

public class CategoryListFragmentPresenter implements Presenter {

    private CategoryListView view;

    @Inject
    public CategoryListFragmentPresenter(CategoryListView view) {
        this.view = view;
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
