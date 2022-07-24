package com.dariuszdeoniziak.charades.statemachines.categories.form;

import static com.dariuszdeoniziak.charades.statemachines.categories.form.CategoriesFormStateMachine.Transition;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.statemachines.Event;
import com.dariuszdeoniziak.charades.statemachines.State;
import com.dariuszdeoniziak.charades.statemachines.categories.form.events.FormLoaded;
import com.dariuszdeoniziak.charades.statemachines.categories.form.events.LoadForm;
import com.dariuszdeoniziak.charades.statemachines.categories.form.events.LoadingError;

public enum CategoriesFormState implements State<Event<Transition, CategoriesFormState>>, CategoriesFormStateMachine.DataReader {

    LOADING(
            new Transition() {
                @Override
                public CategoriesFormState onEvent(LoadForm event) {
                    return null;
                }

                @Override
                public CategoriesFormState onEvent(FormLoaded event) {
                    CategoriesFormState state = CategoriesFormState.EDITING_FORM;
                    state.category = event.category;
                    return state;
                }

                @Override
                public CategoriesFormState onEvent(LoadingError event) {
                    return LOADING_ERROR;
                }
            }
    ),
    LOADING_ERROR(
            new Transition() {
                @Override
                public CategoriesFormState onEvent(LoadForm event) {
                    return LOADING;
                }

                @Override
                public CategoriesFormState onEvent(FormLoaded event) {
                    return null;
                }

                @Override
                public CategoriesFormState onEvent(LoadingError event) {
                    return null;
                }
            }
    ),
    EDITING_FORM(
            new Transition() {
                @Override
                public CategoriesFormState onEvent(LoadForm event) {
                    return LOADING;
                }

                @Override
                public CategoriesFormState onEvent(FormLoaded event) {
                    return null;
                }

                @Override
                public CategoriesFormState onEvent(LoadingError event) {
                    return null;
                }
            }
    );

    private final CategoriesFormStateMachine.Transition transition;

    CategoriesFormState(
            CategoriesFormStateMachine.Transition transition
    ) {
        this.transition = transition;
    }

    @Override
    public CategoriesFormState transition(Event<Transition, CategoriesFormState> event) {
        return event.dispatch(transition);
    }

    private Category category;

    @Override
    public Category getCategory() {
        return category;
    }
}
