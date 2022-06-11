package com.dariuszdeoniziak.charades.coordinators;

import io.reactivex.rxjava3.core.Single;

public interface Coordinator<RESULT> {

    Single<RESULT> coordinate();
}
