package com.dariuszdeoniziak.charades.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.presenters.CategoriesListPresenter;
import com.dariuszdeoniziak.charades.utils.Optional;
import com.dariuszdeoniziak.charades.views.CategoriesListView;
import com.dariuszdeoniziak.charades.views.Layout;
import com.dariuszdeoniziak.charades.views.ViewCall;
import com.dariuszdeoniziak.charades.views.adapters.CategoriesListAdapter;
import com.dariuszdeoniziak.charades.views.adapters.holders.CategoryViewHolder;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import trikita.knork.Knork;


@Layout(R.layout.fragment_categories_list)
public class CategoriesListFragment extends BaseFragment implements CategoriesListView {

    @Knork.Id(R.id.categories_title) TextView categoriesTitleView;
    @Knork.Id(R.id.categories_recycler) RecyclerView categoriesRecyclerView;

    @Inject CategoriesListPresenter presenter;
    @Inject CategoriesListAdapter categoriesListAdapter;

    private Optional<ViewCall.EditCategory> editCategory = Optional.empty();

    public static String TAG = CategoriesListFragment.class.getSimpleName();

    public static CategoriesListFragment newInstance() {
        return new CategoriesListFragment();
    }

    void replace(CategoriesListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        editCategory = Optional.of((ViewCall.EditCategory) context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        categoriesRecyclerView.setAdapter(categoriesListAdapter);
        categoriesListAdapter.setCategoryClickListener(new CategoryViewHolder.CategoryClickListener() {
            @Override
            public void select(Category category) {
                presenter.onSelectCategory(category);
            }

            @Override
            public void edit(Category category) {
                presenter.onEditCategory(category);
            }

            @Override
            public void delete(Category category) {
                presenter.onDeleteCategory(category);
            }
        });
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

    @Override
    public void editCategory(Long categoryId) {
        editCategory.ifPresent((action) -> action.editCategory(categoryId));
    }

    @Override
    public void showConfirmDeleteCategory(Category category) {
        new AlertDialog.Builder(requireActivity())
                .setTitle(R.string.categories_list_dialog_confirm_delete_title)
                .setMessage(getResources().getString(R.string.categories_list_dialog_confirm_delete_message, category.name))
                .setPositiveButton(R.string.yes, (dialog, which) -> presenter.onConfirmDeleteCategory(category))
                .setNegativeButton(R.string.no, null)
                .create().show();
    }
}
