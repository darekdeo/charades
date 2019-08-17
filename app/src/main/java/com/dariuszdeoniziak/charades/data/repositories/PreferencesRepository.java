package com.dariuszdeoniziak.charades.data.repositories;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface PreferencesRepository {

    Completable saveFirstRun();

    Single<Boolean> isFirstRun();

    Completable deleteFirstRun();
}
