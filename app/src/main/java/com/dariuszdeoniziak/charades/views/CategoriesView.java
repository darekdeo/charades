package com.dariuszdeoniziak.charades.views;


public interface CategoriesView extends Router<CategoryScreen>, CategoriesListView.Callback {
    void showTextInfo(String text);

    void toList();

    void toForm();
}
