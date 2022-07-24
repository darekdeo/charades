package com.dariuszdeoniziak.charades.statemachines.categories.form.events;


import com.dariuszdeoniziak.charades.statemachines.Event;
import com.dariuszdeoniziak.charades.statemachines.categories.form.CategoriesFormState;
import com.dariuszdeoniziak.charades.statemachines.categories.form.CategoriesFormStateMachine;

public final class LoadingError implements Event<CategoriesFormStateMachine.Transition, CategoriesFormState> {
    final Throwable error;

    public LoadingError(Throwable error) {
        this.error = error;
    }

    @Override
    public CategoriesFormState dispatch(CategoriesFormStateMachine.Transition transition) {
        return transition.onEvent(this);
    }
}
