package com.dariuszdeoniziak.charades.data.datasources;

import com.dariuszdeoniziak.charades.data.models.room.CategoryRoomModel;
import com.dariuszdeoniziak.charades.data.models.room.CharadeRoomModel;

import java.util.List;


public interface CharadesDataSource {

    void finish();

    long saveCategory(CategoryRoomModel category);
    CategoryRoomModel getCategory(long id);
    List<CategoryRoomModel> getCategories();
    long deleteCategory(CategoryRoomModel category);

    long saveCharade(CharadeRoomModel charade);
    CharadeRoomModel getCharade(long id);
    List<CharadeRoomModel> getCharades();
    List<CharadeRoomModel> getCharades(long categoryId);
    long deleteCharade(CharadeRoomModel charade);
}
