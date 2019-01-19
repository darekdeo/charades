package com.dariuszdeoniziak.charades.views.fragments;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.models.Category;
import com.dariuszdeoniziak.charades.presenters.CategoriesListPresenter;
import com.dariuszdeoniziak.charades.views.CategoriesListView;
import com.dariuszdeoniziak.charades.views.Layout;

import java.util.List;

import javax.inject.Inject;

import trikita.knork.Knork;


@Layout(R.layout.fragment_categories_list)
public class CategoriesListFragment extends BaseFragment implements CategoriesListView {

    @Knork.Id(R.id.categories_title) TextView categoriesTitleView;
    @Knork.Id(R.id.categories_recycler) RecyclerView categoriesRecyclerView;

    @Inject CategoriesListPresenter presenter;

    public static CategoriesListFragment newInstance() {
        return new CategoriesListFragment();
    }

    void replace(CategoriesListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onTakeView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onDropView();
    }

    @Override
    public void hideProgressIndicator() {

    }

    @Override
    public void showProgressIndicator() {

    }

    @Override
    public void showCategories(List<Category> categories) {

    }

    @Override
    public void showEmptyList() {

    }
}
