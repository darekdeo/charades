package com.dariuszdeoniziak.charades.views.fragments;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.models.Category;
import com.dariuszdeoniziak.charades.presenters.CategoryListPresenter;
import com.dariuszdeoniziak.charades.views.CategoryListView;
import com.dariuszdeoniziak.charades.views.Layout;

import java.util.List;

import javax.inject.Inject;

import trikita.knork.Knork;

@Layout(R.layout.fragment_categories)
public class CategoryListFragment extends BaseFragment implements CategoryListView {

    @Knork.Id(R.id.categories_title) TextView categoriesTitleView;
    @Knork.Id(R.id.categories_recycler) RecyclerView categoriesRecyclerView;

    @Inject CategoryListPresenter presenter;

    void replace(CategoryListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onTakeView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDropView();
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
