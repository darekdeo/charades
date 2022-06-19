package com.dariuszdeoniziak.charades.views;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.views.models.CategoriesFormModel;
import com.dariuszdeoniziak.charades.views.models.CharadeListItemModel;

import java.util.List;

import androidx.databinding.ObservableField;


public interface CategoriesFormContract {

    interface View extends com.dariuszdeoniziak.charades.views.View {
        void setup(CategoriesFormModel model);

        void showTextInfo(String text);

        void showCategory(Category category);

        void showCharades(List<CharadeListItemModel> charades);
    }

    interface Presenter extends com.dariuszdeoniziak.charades.presenters.Presenter<View> {
        ObservableField<String> title = new ObservableField<>();

        void onLoadCategory(Long categoryId);

        void onSaveCategoryTitle(String title);
    }

    interface CharadeListItemPresenter {

        void onEdited(CharadeListItemModel charade, String editedText);

        void onDelete(CharadeListItemModel charade);

        void onNew();
    }
}
