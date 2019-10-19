package com.dariuszdeoniziak.charades.views;


public interface CategoriesView extends Router<CategoryScreen>, CategoriesListContract.ParentView {
    void showTextInfo(String text);

    void toList();

    void toForm();
}
