package com.dariuszdeoniziak.charades.views.fragments;

import com.dariuszdeoniziak.charades.navigators.Destination;
import com.dariuszdeoniziak.charades.navigators.DestinationFactory;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class FragmentDestinationFactory implements DestinationFactory.Fragment {

    @Inject
    FragmentDestinationFactory() {}

    @Override
    public Single<BaseFragment> create(Destination destination) {
        return Single.fromCallable(() -> parseInstance(destination));
    }

    private BaseFragment parseInstance(Destination destination) {
        return (BaseFragment) destination.getView();
    }
}
