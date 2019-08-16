package com.dariuszdeoniziak.charades.views;

import com.dariuszdeoniziak.charades.data.models.room.CategoryRoomModel;

import java.util.List;


public interface CategoriesListView {
    void hideProgressIndicator();
    void showProgressIndicator();
    void showCategories(List<CategoryRoomModel> categories);
    void showEmptyList();
}
