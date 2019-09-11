package com.dariuszdeoniziak.charades.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.presenters.CategoriesListPresenter;
import com.dariuszdeoniziak.charades.views.CategoriesListView;
import com.dariuszdeoniziak.charades.views.Layout;
import com.dariuszdeoniziak.charades.views.adapters.CategoriesListAdapter;
import com.dariuszdeoniziak.charades.views.adapters.holders.CategoryViewHolder;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import trikita.knork.Knork;


@Layout(R.layout.fragment_categories_list)
public class CategoriesListFragment extends BaseFragment implements CategoriesListView {

    @Knork.Id(R.id.categories_title) TextView categoriesTitleView;
    @Knork.Id(R.id.categories_recycler) RecyclerView categoriesRecyclerView;

    @Inject CategoriesListPresenter presenter;
    @Inject CategoriesListAdapter categoriesListAdapter;

    public static String TAG = CategoriesListFragment.class.getSimpleName();

    public static CategoriesListFragment newInstance() {
        return new CategoriesListFragment();
    }

    void replace(CategoriesListPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        categoriesRecyclerView.setAdapter(categoriesListAdapter);
        categoriesListAdapter.setCategoryClickListener(new CategoryViewHolder.CategoryClickListener() {
            @Override
            public void edit(Category category) {
                presenter.onEditCategory(category);
            }

            @Override
            public void delete(Category category) {
                presenter.onDeleteCategory(category);
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onTakeView(this);
        presenter.onLoadCategories();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onDropView();
    }

    @Override
    public void hideProgressIndicator() {
        // TODO hide loader
    }

    @Override
    public void showProgressIndicator() {
        // TODO show loader
    }

    @Override
    public void showCategories(List<Category> categories) {
        categoriesListAdapter.adapt(categories);
    }

    @Override
    public void showEmptyList() {
        // TODO show no list items info
    }
}
