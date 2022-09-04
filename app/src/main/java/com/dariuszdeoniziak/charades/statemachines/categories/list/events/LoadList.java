package com.dariuszdeoniziak.charades.statemachines.categories.list.events;

import com.dariuszdeoniziak.charades.statemachines.categories.list.CategoriesListStateMachine;

public final class LoadList implements CategoriesListStateMachine.Event {
    public static final LoadList INSTANCE = new LoadList();

    private LoadList() {
    }

    @Override
    public CategoriesListStateMachine.ResultState dispatch(CategoriesListStateMachine.Transition transition) {
        return transition.onEvent(this);
    }
}
