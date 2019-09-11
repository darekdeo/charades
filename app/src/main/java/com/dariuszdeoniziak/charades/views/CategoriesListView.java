package com.dariuszdeoniziak.charades.views;

import com.dariuszdeoniziak.charades.data.models.Category;

import java.util.List;


public interface CategoriesListView extends ViewCall.EditCategory {
    void hideProgressIndicator();
    void showProgressIndicator();
    void showCategories(List<Category> categories);
    void showEmptyList();
}
