package com.dariuszdeoniziak.charades.views;


public interface CategoriesView extends Router<CategoryScreen>, ViewCall.EditCategory {
    void showTextInfo(String text);

    void toList();

    void toForm();
}
