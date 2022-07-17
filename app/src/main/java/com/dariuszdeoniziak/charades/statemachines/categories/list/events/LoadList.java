package com.dariuszdeoniziak.charades.statemachines.categories.list.events;


import com.dariuszdeoniziak.charades.statemachines.Event;
import com.dariuszdeoniziak.charades.statemachines.categories.list.CategoriesListState;
import com.dariuszdeoniziak.charades.statemachines.categories.list.CategoriesListStateMachine;

public final class LoadList implements Event<CategoriesListStateMachine.Transition, CategoriesListState> {
    public static final LoadList INSTANCE = new LoadList();

    private LoadList() {
    }

    @Override
    public CategoriesListState dispatch(CategoriesListStateMachine.Transition transition) {
        return transition.onEvent(this);
    }
}

