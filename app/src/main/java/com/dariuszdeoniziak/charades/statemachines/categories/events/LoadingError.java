package com.dariuszdeoniziak.charades.statemachines.categories.events;

import com.dariuszdeoniziak.charades.statemachines.Event;
import com.dariuszdeoniziak.charades.statemachines.categories.CategoriesListState;
import com.dariuszdeoniziak.charades.statemachines.categories.CategoriesListStateMachine;

public final class LoadingError implements Event<CategoriesListStateMachine.Transition, CategoriesListState> {
    final Throwable error;

    public LoadingError(Throwable error) {
        this.error = error;
    }

    @Override
    public CategoriesListState dispatch(CategoriesListStateMachine.Transition transition) {
        return transition.onEvent(this);
    }
}

