package com.dariuszdeoniziak.charades.views;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.presenters.Presenter;

import java.util.List;


public interface CategoriesListContract {

    interface View extends com.dariuszdeoniziak.charades.views.View {
        void setTitle(String title);

        void hideProgressIndicator();

        void showProgressIndicator();

        void showCategories(List<Category> categories);

        void showEmptyList();

        void showConfirmDeleteCategory(Category category, String title, String message, String positiveText, String negativeText);
    }

    interface Coordination {
        void selectCategory(Long categoryId);

        void editCategory(Long categoryId);
    }

    interface Presenter extends com.dariuszdeoniziak.charades.presenters.Presenter<View>, ListItemPresenter {
        void onTakeCoordination(Coordination coordination);

        void onLoadCategories();

        void onConfirmDeleteCategory(Category category);

        void onConfirmDeleteCategoryCancelled();
    }

    interface ListItemPresenter {
        void onSelect(Category category);

        void onEdit(Category category);

        void onDelete(Category category);
    }
}
