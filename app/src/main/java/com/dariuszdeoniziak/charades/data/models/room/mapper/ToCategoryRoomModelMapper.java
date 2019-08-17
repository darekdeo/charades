package com.dariuszdeoniziak.charades.data.models.room.mapper;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.data.models.room.CategoryRoomModel;
import com.dariuszdeoniziak.charades.utils.Mapper;


public class ToCategoryRoomModelMapper implements Mapper<Category, CategoryRoomModel> {

    @Override
    public CategoryRoomModel map(Category category) {
        CategoryRoomModel categoryRoomModel = new CategoryRoomModel();
        categoryRoomModel.id = category.id;
        categoryRoomModel.name = category.name;
        categoryRoomModel.description = category.description;
        return categoryRoomModel;
    }
}
