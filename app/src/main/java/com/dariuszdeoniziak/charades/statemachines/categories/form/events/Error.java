package com.dariuszdeoniziak.charades.statemachines.categories.form.events;

import com.dariuszdeoniziak.charades.statemachines.categories.form.CategoriesFormStateMachine;

public final class Error implements CategoriesFormStateMachine.Event {
    final Throwable error;

    public Error(Throwable error) {
        this.error = error;
    }

    @Override
    public CategoriesFormStateMachine.ResultState dispatch(CategoriesFormStateMachine.Transition transition) {
        return transition.onEvent(this);
    }
}
