package com.dariuszdeoniziak.charades.coordinators.categories.destinations;

import com.dariuszdeoniziak.charades.navigators.Destination;
import com.dariuszdeoniziak.charades.views.CategoriesListContract.View;
import com.dariuszdeoniziak.charades.views.CategoriesListContract.Presenter;

import javax.inject.Inject;

public class ListDestination implements Destination {

    private final View view;
    private final Presenter presenter;

    @Inject
    ListDestination(
            View view,
            Presenter presenter
    ) {
        this.view = view;
        this.presenter = presenter;
    }

    @Override
    public String getTag() {
        return this.getClass().toString();
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public Presenter getPresenter() {
        return presenter;
    }
}
