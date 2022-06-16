package com.dariuszdeoniziak.charades.navigators;

import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;

import io.reactivex.rxjava3.core.Single;

public interface DestinationFactory<T> {

    Single<T> create(Destination destination);

    interface Fragment extends DestinationFactory<BaseFragment> {}
}
