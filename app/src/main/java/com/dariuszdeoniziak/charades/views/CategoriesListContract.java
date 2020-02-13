package com.dariuszdeoniziak.charades.views;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.views.models.CategoriesListModel;

import java.util.List;


public interface CategoriesListContract {

    interface View {
        void setup(CategoriesListModel model);

        void hideProgressIndicator();

        void showProgressIndicator();

        void showCategories(List<Category> categories);

        void showEmptyList();

        void showConfirmDeleteCategory(Category category, String title, String message, String positiveText, String negativeText);

        void selectCategory(Long categoryId);

        void editCategory(Long categoryId);
    }

    interface ParentView {

        void selectCategory(Long categoryId);

        void editCategory(Long categoryId);
    }

    interface Presenter {

        void onLoadCategories();

        void onConfirmDeleteCategory(Category category);
    }

    interface ListItemPresenter {

        void onSelect(Category category);

        void onEdit(Category category);

        void onDelete(Category category);
    }
}
