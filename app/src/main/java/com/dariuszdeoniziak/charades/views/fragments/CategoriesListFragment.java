package com.dariuszdeoniziak.charades.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.databinding.FragmentCategoriesListBinding;
import com.dariuszdeoniziak.charades.presenters.CategoriesListPresenter;
import com.dariuszdeoniziak.charades.presenters.Presenter;
import com.dariuszdeoniziak.charades.utils.Optional;
import com.dariuszdeoniziak.charades.views.CategoriesListContract;
import com.dariuszdeoniziak.charades.views.adapters.CategoriesListAdapter;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;


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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
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
//        categoriesListAdapter.setPresenter(presenter);
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
    }
}
