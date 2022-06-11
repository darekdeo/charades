package com.dariuszdeoniziak.charades.statemachines.categories.list.events;

import com.dariuszdeoniziak.charades.statemachines.Event;
import com.dariuszdeoniziak.charades.statemachines.categories.list.CategoriesListStateMachine;
import com.dariuszdeoniziak.charades.statemachines.categories.list.CategoriesListState;

public final class DeleteCategoryCancel implements Event<CategoriesListStateMachine.Transition, CategoriesListState> {

    public static final DeleteCategoryCancel INSTANCE = new DeleteCategoryCancel();

    private DeleteCategoryCancel() {
    }

    @Override
    public CategoriesListState dispatch(CategoriesListStateMachine.Transition transition) {
        return transition.onEvent(this);
    }
}
