package com.dariuszdeoniziak.charades.statemachines.categories.form.events;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.statemachines.Event;
import com.dariuszdeoniziak.charades.statemachines.categories.form.CategoriesFormState;
import com.dariuszdeoniziak.charades.statemachines.categories.form.CategoriesFormStateMachine;

public final class FormLoaded implements Event<CategoriesFormStateMachine.Transition, CategoriesFormState> {
    final public Category category;

    public FormLoaded(Category category) {
        this.category = category;
    }

    @Override
    public CategoriesFormState dispatch(CategoriesFormStateMachine.Transition transition) {
        return transition.onEvent(this);
    }
}
