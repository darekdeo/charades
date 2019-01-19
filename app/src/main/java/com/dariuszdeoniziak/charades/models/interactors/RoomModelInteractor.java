package com.dariuszdeoniziak.charades.models.interactors;

import com.dariuszdeoniziak.charades.models.Category;
import com.dariuszdeoniziak.charades.models.Charade;
import com.dariuszdeoniziak.charades.models.CharadesRoomDatabase;

import java.util.List;

import javax.inject.Inject;


public class RoomModelInteractor implements ModelInteractor {

    private final CharadesRoomDatabase database;

    @Inject
    public RoomModelInteractor(CharadesRoomDatabase database) {
        this.database = database;
    }

    @Override
    public void finish() {
        database.close();
    }

    @Override
    public long saveCategory(Category category) {
        long id = database.getCategoryDao().update(category);
        if (id == 0) {
            id = database.getCategoryDao().insert(category);
        }
        return id;
    }

    @Override
    public Category getCategory(long id) {
        return database.getCategoryDao().getById(id);
    }

    @Override
    public List<Category> getCategories() {
        return database.getCategoryDao().getAll();
    }

    @Override
    public long deleteCategory(Category category) {
        return database.getCategoryDao().delete(category);
    }

    @Override
    public long saveCharade(Charade charade) {
        long id = database.getCharadeDao().update(charade);
        if (id == 0) {
            id = database.getCharadeDao().insert(charade);
        }
        return id;
    }

    @Override
    public Charade getCharade(long id) {
        return database.getCharadeDao().getById(id);
    }

    @Override
    public List<Charade> getCharades() {
        return database.getCharadeDao().getAll();
    }

    @Override
    public List<Charade> getCharades(long categoryId) {
        return database.getCharadeDao().getAllForCategory(categoryId);
    }

    @Override
    public long deleteCharade(Charade charade) {
        return database.getCharadeDao().delete(charade);
    }
}
