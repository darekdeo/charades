package com.dariuszdeoniziak.charades.views;

import com.dariuszdeoniziak.charades.data.models.room.CategoryRoomModel;


public interface CategoriesFormView {
    void showTextInfo(String text);
    void showCategory(CategoryRoomModel category);
}
