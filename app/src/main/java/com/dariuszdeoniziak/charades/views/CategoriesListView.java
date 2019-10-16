package com.dariuszdeoniziak.charades.views;

import com.dariuszdeoniziak.charades.data.models.Category;

import java.util.List;


public interface CategoriesListView {
    void hideProgressIndicator();
    void showProgressIndicator();
    void showCategories(List<Category> categories);
    void showEmptyList();
    void showConfirmDeleteCategory(Category category);

    void selectCategory(Long categoryId);
    void editCategory(Long categoryId);

    interface Callback {
        void selectCategory(Long categoryId);
        void editCategory(Long categoryId);
    }
}
