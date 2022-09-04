package com.dariuszdeoniziak.charades.statemachines.categories.form.events;

import com.dariuszdeoniziak.charades.statemachines.categories.form.CategoriesFormStateMachine;

public final class LoadForm implements CategoriesFormStateMachine.Event {
    public static final LoadForm INSTANCE = new LoadForm();

    private LoadForm() {
    }

    @Override
    public CategoriesFormStateMachine.ResultState dispatch(CategoriesFormStateMachine.Transition transition) {
        return transition.onEvent(this);
    }
}
