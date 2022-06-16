package com.dariuszdeoniziak.charades.views.fragments;

import com.dariuszdeoniziak.charades.coordinators.categories.CategoryDestination;
import com.dariuszdeoniziak.charades.navigators.Destination;
import com.dariuszdeoniziak.charades.navigators.DestinationFactory;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;

public class FragmentDestinationFactory implements DestinationFactory.Fragment {

    @Inject
    FragmentDestinationFactory() {}

    @Override
    public Single<BaseFragment> create(Destination destination) {
        return Single
                .just(destination)
                .ofType(CategoryDestination.class)
                .map((Function<CategoryDestination, BaseFragment>) categoryDestination -> {
                    BaseFragment fragment;
                    switch (categoryDestination) {
                        case LIST:
                            fragment = new CategoriesListFragment();
                            break;
                        case FORM:
                            fragment = new CategoriesFormFragment();
                            break;
                        default:
                            fragment = null;
                            break;
                    }
                    return fragment;
                })
                .toSingle();
    }
}
