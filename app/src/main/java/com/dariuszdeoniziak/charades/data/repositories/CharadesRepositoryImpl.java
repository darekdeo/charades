package com.dariuszdeoniziak.charades.data.repositories;

import com.dariuszdeoniziak.charades.data.datasources.CharadesDataSource;
import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.data.models.Charade;
import com.dariuszdeoniziak.charades.data.models.room.CategoryRoomModel;
import com.dariuszdeoniziak.charades.data.models.room.CharadeRoomModel;
import com.dariuszdeoniziak.charades.utils.Mapper;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;


public class CharadesRepositoryImpl implements CharadesRepository {

    private final CharadesDataSource charadesLocalDataSource;
    private final Mapper<Category, CategoryRoomModel> toCategoryRoomModelMapper;
    private final Mapper<CategoryRoomModel, Category> fromCategoryRoomModelMapper;
    private final Mapper<Charade, CharadeRoomModel> toCharadeRoomModelMapper;
    private final Mapper<CharadeRoomModel, Charade> fromCharadeRoomModelMapper;

    public CharadesRepositoryImpl(
            CharadesDataSource charadesLocalDataSource,
            Mapper<Category, CategoryRoomModel> toCategoryRoomModelMapper,
            Mapper<CategoryRoomModel, Category> fromCategoryRoomModelMapper,
            Mapper<Charade, CharadeRoomModel> toCharadeRoomModelMapper,
            Mapper<CharadeRoomModel, Charade> fromCharadeRoomModelMapper
    ) {
        this.charadesLocalDataSource = charadesLocalDataSource;
        this.toCategoryRoomModelMapper = toCategoryRoomModelMapper;
        this.fromCategoryRoomModelMapper = fromCategoryRoomModelMapper;
        this.toCharadeRoomModelMapper = toCharadeRoomModelMapper;
        this.fromCharadeRoomModelMapper = fromCharadeRoomModelMapper;
    }

    @Override
    public Single<Long> saveCategory(Category category) {
        return Single.just(category)
                .map(toCategoryRoomModelMapper::map)
                .map(charadesLocalDataSource::saveCategory);
    }

    @Override
    public Single<Category> getCategory(long id) {
        return Single.just(id)
                .map(charadesLocalDataSource::getCategory)
                .map(fromCategoryRoomModelMapper::map);
    }

    @Override
    public Single<List<Category>> getCategories() {
        return Observable.fromIterable(charadesLocalDataSource.getCategories())
                .map(fromCategoryRoomModelMapper::map)
                .toList();
    }

    @Override
    public Single<Long> deleteCategory(Category category) {
        return Single.just(category)
                .map(toCategoryRoomModelMapper::map)
                .map(charadesLocalDataSource::deleteCategory);
    }

    @Override
    public Single<Long> saveCharade(Charade charade) {
        return Single.just(charade)
                .map(toCharadeRoomModelMapper::map)
                .map(charadesLocalDataSource::saveCharade);
    }

    @Override
    public Single<Charade> getCharade(long id) {
        return Single.just(id)
                .map(charadesLocalDataSource::getCharade)
                .map(fromCharadeRoomModelMapper::map);
    }

    @Override
    public Single<List<Charade>> getCharades() {
        return Observable.fromIterable(charadesLocalDataSource.getCharades())
                .map(fromCharadeRoomModelMapper::map)
                .toList();
    }

    @Override
    public Single<List<Charade>> getCharades(long categoryId) {
        return Observable.just(categoryId)
                .map(charadesLocalDataSource::getCharades)
                .flatMapIterable((items) -> items)
                .map(fromCharadeRoomModelMapper::map)
                .toList();
    }

    @Override
    public Single<Long> deleteCharade(Charade charade) {
        return Single.just(charade)
                .map(toCharadeRoomModelMapper::map)
                .map(charadesLocalDataSource::deleteCharade);
    }
}
