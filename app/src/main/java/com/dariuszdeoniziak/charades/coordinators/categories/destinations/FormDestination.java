package com.dariuszdeoniziak.charades.coordinators.categories.destinations;

import com.dariuszdeoniziak.charades.navigators.Destination;
import com.dariuszdeoniziak.charades.views.CategoriesFormContract.View;
import com.dariuszdeoniziak.charades.views.CategoriesFormContract.Presenter;

import javax.inject.Inject;

public class FormDestination implements Destination {

    private final View view;
    private final Presenter presenter;

    @Inject
    FormDestination(
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
