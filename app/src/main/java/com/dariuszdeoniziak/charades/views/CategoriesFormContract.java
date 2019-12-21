package com.dariuszdeoniziak.charades.views;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.views.models.CharadeListItemModel;

import java.util.List;


public interface CategoriesFormContract {

    interface View {
        void showTextInfo(String text);

        void showCategory(Category category);

        void showCharades(List<CharadeListItemModel>charades);
    }

    interface CharadeListItemPresenter {

        void onEdited(CharadeListItemModel charade, String editedText);

        void onDelete(CharadeListItemModel charade);

        void onNew();
    }
}
