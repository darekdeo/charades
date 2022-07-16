package com.dariuszdeoniziak.charades.views.fragments;

import com.dariuszdeoniziak.charades.navigators.Destination;
import com.dariuszdeoniziak.charades.navigators.DestinationParser;

import io.reactivex.rxjava3.core.Single;

public class FragmentDestinationParser implements DestinationParser.Fragment {

    @Override
    public Single<BaseFragment> parse(Destination<?> destination) {
        return Single.fromCallable(() -> castInstance(destination));
    }

    private BaseFragment castInstance(Destination<?> destination) {
        return (BaseFragment) destination.getView();
    }
}
