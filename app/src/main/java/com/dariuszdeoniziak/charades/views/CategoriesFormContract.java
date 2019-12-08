package com.dariuszdeoniziak.charades.views;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.data.models.Charade;

import java.util.List;


public interface CategoriesFormContract {

    interface View {
        void showTextInfo(String text);

        void showCategory(Category category);

        void showCharades(List<Charade>charades);
    }

    interface ListItemPresenter {

        void onEdited(Charade category, String editedText);

        void onDelete(Charade category);
    }
}
