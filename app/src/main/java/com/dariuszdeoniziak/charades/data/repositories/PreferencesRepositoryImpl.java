package com.dariuszdeoniziak.charades.data.repositories;

import com.dariuszdeoniziak.charades.data.datasources.PreferencesDataSource;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class PreferencesRepositoryImpl implements PreferencesRepository {

    private final PreferencesDataSource preferencesLocalDataSource;

    public PreferencesRepositoryImpl(PreferencesDataSource preferencesLocalDataSource) {
        this.preferencesLocalDataSource = preferencesLocalDataSource;
    }

    @Override
    public Completable saveFirstRun() {
        return Completable.fromAction(preferencesLocalDataSource::saveFirstRun);
    }

    @Override
    public Single<Boolean> isFirstRun() {
        return Single.just(preferencesLocalDataSource.isFirstRun());
    }

    @Override
    public Completable deleteFirstRun() {
        return Completable.fromAction(preferencesLocalDataSource::deleteFirstRun);
    }
}
