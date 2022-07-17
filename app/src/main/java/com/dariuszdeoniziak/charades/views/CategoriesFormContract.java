package com.dariuszdeoniziak.charades.views;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.views.models.CategoriesFormModel;
import com.dariuszdeoniziak.charades.views.models.CharadeListItemModel;

import java.util.List;


public interface CategoriesFormContract {

    interface View extends com.dariuszdeoniziak.charades.views.View {
        void setup(CategoriesFormModel model);

        void showTextInfo(String text);

        void showCategory(Category category);

        void showCharades(List<CharadeListItemModel> charades);
    }

    interface Presenter extends com.dariuszdeoniziak.charades.presenters.Presenter<View>, CharadeListItemPresenter {
        void onTakeCoordination(Coordination coordination);

        void onNewCategory();

        void onLoadCategory(Long categoryId);

        void onSaveCategoryTitle(String title);

        void onClose();
    }

    interface CharadeListItemPresenter {

        void onEdited(CharadeListItemModel charade, String editedText);

        void onDelete(CharadeListItemModel charade);

        void onNew();
    }

    interface Coordination {
        void closeCategoryForm();
    }
}
