package com.dariuszdeoniziak.charades.models.interactors;

import com.dariuszdeoniziak.charades.models.Category;
import com.dariuszdeoniziak.charades.models.Charade;

import java.util.List;


public interface ModelInteractor {

    void finish();

    long saveCategory(Category category);
    Category getCategory(long id);
    List<Category> getCategories();
    long deleteCategory(Category category);

    long saveCharade(Charade charade);
    Charade getCharade(long id);
    List<Charade> getCharades();
    List<Charade> getCharades(long categoryId);
    long deleteCharade(Charade charade);
}
