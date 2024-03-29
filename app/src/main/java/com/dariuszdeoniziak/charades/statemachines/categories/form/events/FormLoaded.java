package com.dariuszdeoniziak.charades.statemachines.categories.form.events;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.statemachines.categories.form.CategoriesFormStateMachine;

public final class FormLoaded implements CategoriesFormStateMachine.Event {
    final public Category category;

    public FormLoaded(Category category) {
        this.category = category;
    }

    @Override
    public CategoriesFormStateMachine.ResultState dispatch(CategoriesFormStateMachine.Transition transition) {
        return transition.onEvent(this);
    }
}
