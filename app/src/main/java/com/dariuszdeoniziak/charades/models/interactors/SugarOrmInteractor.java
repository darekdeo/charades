package com.dariuszdeoniziak.charades.models.interactors;

import android.content.Context;

import com.dariuszdeoniziak.charades.models.Category;
import com.dariuszdeoniziak.charades.models.Charade;
import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.List;

import javax.inject.Inject;

public class SugarOrmInteractor implements ModelInteractor {

    @Inject
    public SugarOrmInteractor(Context context) {
        SugarContext.init(context);
    }

    @Override
    public void finish() {
        SugarContext.terminate();
    }

    @Override
    public long saveCategory(Category category) {
        return SugarRecord.save(category);
    }

    @Override
    public Category getCategory(long id) {
        return SugarRecord.findById(Category.class, id);
    }

    @Override
    public List<Category> getCategories() {
        return SugarRecord.listAll(Category.class);
    }

    @Override
    public boolean deleteCategory(Category category) {
        return SugarRecord.delete(category);
    }

    @Override
    public long saveCharade(Charade charade) {
        return SugarRecord.save(charade);
    }

    @Override
    public Charade getCharade(long id) {
        return SugarRecord.findById(Charade.class, id);
    }

    @Override
    public List<Charade> getCharades() {
        return SugarRecord.listAll(Charade.class);
    }

    @Override
    public List<Charade> getCharades(Category category) {
        return category.getCharades();
    }

    @Override
    public boolean deleteCharade(Charade charade) {
        return SugarRecord.delete(charade);
    }
}
