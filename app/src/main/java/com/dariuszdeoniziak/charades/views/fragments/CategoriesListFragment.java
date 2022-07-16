package com.dariuszdeoniziak.charades.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.databinding.FragmentCategoriesListBinding;
import com.dariuszdeoniziak.charades.views.CategoriesListContract;
import com.dariuszdeoniziak.charades.views.adapters.CategoriesListAdapter;

import java.util.List;

import javax.inject.Inject;


public class CategoriesListFragment extends BaseFragment implements CategoriesListContract.View {

    private final CategoriesListContract.Presenter presenter;
    private final CategoriesListAdapter categoriesListAdapter;

    private FragmentCategoriesListBinding binding;

    public static String TAG = CategoriesListFragment.class.getSimpleName();

    @Inject
    CategoriesListFragment(CategoriesListContract.Presenter presenter, CategoriesListAdapter categoriesListAdapter) {
        this.presenter = presenter;
        this.categoriesListAdapter = categoriesListAdapter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCategoriesListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.categoriesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.categoriesRecycler.setAdapter(categoriesListAdapter);
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
    public void setTitle(String title) {
        binding.setTitle(title);
        binding.invalidateAll();
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
    public void showConfirmDeleteCategory(Category category, String title, String message, String positiveButton, String negativeButton) {
        // TODO show delete confirmation dialog
    }
}
