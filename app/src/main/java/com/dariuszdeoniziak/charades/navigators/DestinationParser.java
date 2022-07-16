package com.dariuszdeoniziak.charades.navigators;

import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;

import io.reactivex.rxjava3.core.Single;

public interface DestinationParser<T> {

    Single<T> parse(Destination<?> destination);

    interface Fragment extends DestinationParser<BaseFragment> {}
}
