package com.dariuszdeoniziak.charades.statemachines.categories.form;

import static com.dariuszdeoniziak.charades.statemachines.categories.form.CategoriesFormStateMachine.Transition;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.statemachines.categories.form.events.Error;
import com.dariuszdeoniziak.charades.statemachines.categories.form.events.FormLoaded;
import com.dariuszdeoniziak.charades.statemachines.categories.form.events.LoadForm;
import com.dariuszdeoniziak.charades.utils.Optional;

public enum CategoriesFormState implements CategoriesFormStateMachine.State {

    LOADING(
            new Transition() {
                @Override
                public CategoriesFormStateMachine.ResultState onEvent(FormLoaded event) {
                    return valid(EDITING_FORM, Optional.of(event.category));
                }

                @Override
                public CategoriesFormStateMachine.ResultState onEvent(Error event) {
                    return valid(ERROR, Optional.empty());
                }
            }
    ),
    ERROR(
            new Transition() {
                @Override
                public CategoriesFormStateMachine.ResultState onEvent(LoadForm event) {
                    return valid(LOADING, Optional.empty());
                }
            }
    ),
    EDITING_FORM(
            new Transition() {
                @Override
                public CategoriesFormStateMachine.ResultState onEvent(LoadForm event) {
                    return valid(LOADING, Optional.empty());
                }
            }
    );

    private final CategoriesFormStateMachine.Transition transition;

    static CategoriesFormStateMachine.ResultState valid(CategoriesFormState state, Optional<Category> category) {
        return new CategoriesFormStateMachine.ResultState() {
            @Override
            public CategoriesFormState state() {
                return state;
            }

            @Override
            public Optional<Category> getCategory() {
                return category;
            }
        };
    }

    static CategoriesFormStateMachine.ResultState invalid() {
        return new CategoriesFormStateMachine.ResultState() {
            @Override
            public CategoriesFormState state() {
                return ERROR;
            }

            @Override
            public Optional<Category> getCategory() {
                return Optional.empty();
            }
        };
    }

    static CategoriesFormStateMachine.ResultState defaultResultState() {
        return new CategoriesFormStateMachine.ResultState() {

            @Override
            public CategoriesFormState state() {
                return LOADING;
            }

            @Override
            public Optional<Category> getCategory() {
                return Optional.empty();
            }
        };
    }

    CategoriesFormState(
            CategoriesFormStateMachine.Transition transition
    ) {
        this.transition = transition;
    }

    @Override
    public CategoriesFormStateMachine.ResultState transition(CategoriesFormStateMachine.Event event) {
        return event.dispatch(transition);
    }
}
