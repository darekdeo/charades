package com.dariuszdeoniziak.charades.data.repositories;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface PreferencesRepository {

    Completable saveFirstRun();

    Single<Boolean> isFirstRun();

    Completable deleteFirstRun();
}
