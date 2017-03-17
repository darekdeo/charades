package com.dariuszdeoniziak.charades.presenters;

import android.os.AsyncTask;

import com.dariuszdeoniziak.charades.models.Category;
import com.dariuszdeoniziak.charades.models.interactors.ModelInteractor;
import com.dariuszdeoniziak.charades.views.CategoryListView;
import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;
import com.dariuszdeoniziak.charades.views.fragments.CategoryListFragment;

import java.util.List;

import javax.inject.Inject;

import static android.R.attr.fragment;

public class CategoryListPresenter implements Presenter<CategoryListView> {

    CategoryListView view;
    ModelInteractor modelInteractor;

    @Inject
    public CategoryListPresenter(ModelInteractor modelInteractor) {
        this.modelInteractor = modelInteractor;
    }

    @Override
    public void onSave() {

    }

    @Override
    public void onTakeView(CategoryListView view) {
        this.view = view;
    }

    @Override
    public void onDropView() {
        view = null;
    }

    public void loadCategories() {
        view.showProgressIndicator();
        List<Category> categories = modelInteractor.getCategories();
        if (categories.isEmpty())
            view.showEmptyList();
        else
            view.showCategories(categories);
    }
}
