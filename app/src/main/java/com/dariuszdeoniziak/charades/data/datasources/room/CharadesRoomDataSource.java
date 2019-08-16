package com.dariuszdeoniziak.charades.data.datasources.room;

import com.dariuszdeoniziak.charades.data.datasources.CharadesDataSource;
import com.dariuszdeoniziak.charades.data.models.room.CategoryRoomModel;
import com.dariuszdeoniziak.charades.data.models.room.CharadeRoomModel;

import java.util.List;

import javax.inject.Inject;


public class CharadesRoomDataSource implements CharadesDataSource {

    private final CharadesRoomDatabase database;

    @Inject
    public CharadesRoomDataSource(CharadesRoomDatabase database) {
        this.database = database;
    }

    @Override
    public void finish() {
        database.close();
    }

    @Override
    public long saveCategory(CategoryRoomModel category) {
        long id = database.getCategoryDao().update(category);
        if (id == 0) {
            id = database.getCategoryDao().insert(category);
        }
        return id;
    }

    @Override
    public CategoryRoomModel getCategory(long id) {
        return database.getCategoryDao().getById(id);
    }

    @Override
    public List<CategoryRoomModel> getCategories() {
        return database.getCategoryDao().getAll();
    }

    @Override
    public long deleteCategory(CategoryRoomModel category) {
        return database.getCategoryDao().delete(category);
    }

    @Override
    public long saveCharade(CharadeRoomModel charade) {
        long id = database.getCharadeDao().update(charade);
        if (id == 0) {
            id = database.getCharadeDao().insert(charade);
        }
        return id;
    }

    @Override
    public CharadeRoomModel getCharade(long id) {
        return database.getCharadeDao().getById(id);
    }

    @Override
    public List<CharadeRoomModel> getCharades() {
        return database.getCharadeDao().getAll();
    }

    @Override
    public List<CharadeRoomModel> getCharades(long categoryId) {
        return database.getCharadeDao().getAllForCategory(categoryId);
    }

    @Override
    public long deleteCharade(CharadeRoomModel charade) {
        return database.getCharadeDao().delete(charade);
    }
}
