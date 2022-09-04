package com.dariuszdeoniziak.charades.statemachines.categories.list.events;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.statemachines.categories.list.CategoriesListStateMachine;

public final class DeleteCategory implements CategoriesListStateMachine.Event {
    final public Category category;

    public DeleteCategory(Category category) {
        this.category = category;
    }

    @Override
    public CategoriesListStateMachine.ResultState dispatch(CategoriesListStateMachine.Transition transition) {
        return transition.onEvent(this);
    }
}
