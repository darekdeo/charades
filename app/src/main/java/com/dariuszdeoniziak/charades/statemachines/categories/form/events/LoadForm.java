package com.dariuszdeoniziak.charades.statemachines.categories.form.events;


import com.dariuszdeoniziak.charades.statemachines.Event;
import com.dariuszdeoniziak.charades.statemachines.categories.form.CategoriesFormState;
import com.dariuszdeoniziak.charades.statemachines.categories.form.CategoriesFormStateMachine;

public final class LoadForm implements Event<CategoriesFormStateMachine.Transition, CategoriesFormState> {
    public static final LoadForm INSTANCE = new LoadForm();

    private LoadForm() {
    }

    @Override
    public CategoriesFormState dispatch(CategoriesFormStateMachine.Transition transition) {
        return transition.onEvent(this);
    }
}
