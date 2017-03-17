package com.dariuszdeoniziak.charades.views.fragments;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.presenters.CategoryListFragmentPresenter;
import com.dariuszdeoniziak.charades.views.Layout;

import javax.inject.Inject;

import trikita.knork.Knork;

@Layout(R.layout.fragment_categories)
public class CategoryListFragment extends BaseFragment {

    @Knork.Id(R.id.categories_title) TextView categoriesTitleView;
    @Knork.Id(R.id.categories_recycler) RecyclerView categoriesRecyclerView;

    @Inject CategoryListFragmentPresenter presenter;

    void replace(CategoryListFragmentPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onTakeView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDropView();
    }
}
