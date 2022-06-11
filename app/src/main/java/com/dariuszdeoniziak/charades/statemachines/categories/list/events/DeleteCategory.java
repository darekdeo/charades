package com.dariuszdeoniziak.charades.statemachines.categories.list.events;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.statemachines.Event;
import com.dariuszdeoniziak.charades.statemachines.categories.list.CategoriesListState;
import com.dariuszdeoniziak.charades.statemachines.categories.list.CategoriesListStateMachine;

public final class DeleteCategory implements Event<CategoriesListStateMachine.Transition, CategoriesListState> {
    public final Category category;

    public DeleteCategory(Category category) {
        this.category = category;
    }

    @Override
    public CategoriesListState dispatch(CategoriesListStateMachine.Transition transition) {
        return transition.onEvent(this);
    }
}

