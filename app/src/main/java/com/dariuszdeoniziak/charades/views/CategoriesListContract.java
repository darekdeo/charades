package com.dariuszdeoniziak.charades.views;

import com.dariuszdeoniziak.charades.data.models.Category;

import java.util.List;


public interface CategoriesListContract {

    interface CategoriesListView {
        void hideProgressIndicator();

        void showProgressIndicator();

        void showCategories(List<Category> categories);

        void showEmptyList();

        void showConfirmDeleteCategory(Category category);

        void selectCategory(Long categoryId);

        void editCategory(Long categoryId);
    }

    interface ParentView {
        void selectCategory(Long categoryId);

        void editCategory(Long categoryId);
    }

    interface ListItemPresenter {

        void onSelect(Category category);

        void onEdit(Category category);

        void onDelete(Category category);
    }
}
