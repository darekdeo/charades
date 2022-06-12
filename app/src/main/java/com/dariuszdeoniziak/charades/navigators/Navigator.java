package com.dariuszdeoniziak.charades.navigators;

import io.reactivex.rxjava3.core.Completable;

public interface Navigator {

    Completable navigate(Destination destination);

    interface Screen extends Navigator {}
}
