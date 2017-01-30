package com.dariuszdeoniziak.charades.models.interactors;

import android.content.Context;

import com.dariuszdeoniziak.charades.models.Category;
import com.dariuszdeoniziak.charades.models.Charade;
import com.orm.SugarContext;

import java.util.List;

import javax.inject.Inject;

public class SugarOrmInteractor implements ModelInteractor {

    @Inject
    @Override
    public void init(Context context) {
        SugarContext.init(context);
    }

    @Override
    public void finish() {
        SugarContext.terminate();
    }

    @Override
    public long saveCategory(Category category) {
        return category.save();
    }

    @Override
    public Category getCategory(long id) {
        return Category.findById(Category.class, id);
    }

    @Override
    public List<Category> getCategories() {
        return Category.listAll(Category.class);
    }

    @Override
    public boolean deleteCategory(Category category) {
        return Category.delete(category);
    }

    @Override
    public long saveCharade(Charade charade) {
        return charade.save();
    }

    @Override
    public Charade getCharade(long id) {
        return Charade.findById(Charade.class, id);
    }

    @Override
    public List<Charade> getCharades() {
        return Charade.listAll(Charade.class);
    }

    @Override
    public List<Charade> getCharades(Category category) {
        return category.getCharades();
    }

    @Override
    public boolean deleteCharade(Charade charade) {
        return charade.delete();
    }
}
