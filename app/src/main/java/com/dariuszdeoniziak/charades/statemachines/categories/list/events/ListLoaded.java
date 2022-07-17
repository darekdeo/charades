package com.dariuszdeoniziak.charades.statemachines.categories.list.events;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.statemachines.Event;
import com.dariuszdeoniziak.charades.statemachines.categories.list.CategoriesListState;
import com.dariuszdeoniziak.charades.statemachines.categories.list.CategoriesListStateMachine;

import java.util.List;

public final class ListLoaded implements Event<CategoriesListStateMachine.Transition, CategoriesListState> {
    final public List<Category> categories;

    public ListLoaded(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public CategoriesListState dispatch(CategoriesListStateMachine.Transition transition) {
        return transition.onEvent(this);
    }
}

