package com.dariuszdeoniziak.charades.statemachines.categories.list.events;

import com.dariuszdeoniziak.charades.statemachines.categories.list.CategoriesListStateMachine;

public final class Error implements CategoriesListStateMachine.Event {
    final Throwable error;

    public Error(Throwable error) {
        this.error = error;
    }

    @Override
    public CategoriesListStateMachine.ResultState dispatch(CategoriesListStateMachine.Transition transition) {
        return transition.onEvent(this);
    }
}
