package com.dariuszdeoniziak.charades.data.repositories;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.data.models.Charade;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface CharadesRepository {

    Single<Long> saveCategory(Category category);

    Single<Category> getCategory(long id);

    Single<List<Category>> getCategories();

    Single<Long> deleteCategory(Category category);

    Single<Long> saveCharade(Charade charade);

    Single<Charade> getCharade(long id);

    Single<List<Charade>> getCharades();

    Single<List<Charade>> getCharades(long categoryId);

    Single<Long> deleteCharade(Charade charade);
}
