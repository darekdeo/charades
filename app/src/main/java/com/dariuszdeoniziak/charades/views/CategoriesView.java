package com.dariuszdeoniziak.charades.views;


public interface CategoriesView extends Router<CategoryScreen> {
    void showTextInfo(String text);

    void toList();

    void toForm();

    void toEditForm(Integer categoryId);
}
