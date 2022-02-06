package com.dariuszdeoniziak.charades.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.databinding.FragmentCategoriesListBinding;
import com.dariuszdeoniziak.charades.presenters.CategoriesListPresenter;
import com.dariuszdeoniziak.charades.utils.Optional;
import com.dariuszdeoniziak.charades.views.CategoriesListContract;
import com.dariuszdeoniziak.charades.views.ComponentsFacade;
import com.dariuszdeoniziak.charades.views.adapters.CategoriesListAdapter;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;


public class CategoriesListFragment extends BaseFragment implements CategoriesListContract.View {

    @Inject CategoriesListPresenter presenter;
    @Inject CategoriesListAdapter categoriesListAdapter;

    private FragmentCategoriesListBinding binding;
    private Optional<CategoriesListContract.ParentView> parentView = Optional.empty();

    public static String TAG = CategoriesListFragment.class.getSimpleName();

    public static CategoriesListFragment newInstance() {
        return new CategoriesListFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        parentView = Optional.of((CategoriesListContract.ParentView) context);
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
        categoriesListAdapter.setPresenter(presenter);
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
    public void selectCategory(Long categoryId) {
        parentView.ifPresent((action) -> action.selectCategory(categoryId));
    }

    @Override
    public void editCategory(Long categoryId) {
        parentView.ifPresent((action) -> action.editCategory(categoryId));
    }

    @Override
    public void showConfirmDeleteCategory(Category category, String title, String message, String positiveButton, String negativeButton) {
        componentsFacade.showDialog(new ComponentsFacade.DialogTemplate() {
            @Override
            public String title() {
                return title;
            }

            @Override
            public String message() {
                return message;
            }

            @Override
            public String positiveButtonText() {
                return positiveButton;
            }

            @Override
            public String negativeButtonText() {
                return negativeButton;
            }

            @Override
            public void negativeCallback() {
                presenter.onConfirmDeleteCategoryCancelled();
            }

            @Override
            public void positiveCallback() {
                presenter.onConfirmDeleteCategory(category);
            }
        });
    }
}
