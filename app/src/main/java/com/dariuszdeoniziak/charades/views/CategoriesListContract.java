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
    }

    interface Coordination {
        void selectCategory(Long categoryId);

        void editCategory(Long categoryId);

        void addNewCategory();

        void showConfirmDeleteCategory(Category category);
    }

    interface Presenter extends com.dariuszdeoniziak.charades.presenters.Presenter<View>, ListItemPresenter {
        void onTakeCoordination(Coordination coordination);

        void onLoadCategories();

        void onDeleteCategory(Category category);

        void onAddNew();
    }

    interface ListItemPresenter {
        void onSelect(Category category);

        void onEdit(Category category);

        void onDelete(Category category);
    }
}
