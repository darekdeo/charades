package com.dariuszdeoniziak.charades.statemachines.categories.list;

import static com.dariuszdeoniziak.charades.statemachines.categories.list.CategoriesListStateMachine.ResultState;
import static com.dariuszdeoniziak.charades.statemachines.categories.list.CategoriesListStateMachine.State;
import static com.dariuszdeoniziak.charades.statemachines.categories.list.CategoriesListStateMachine.Transition;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.DeleteCategory;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.Error;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.ListLoaded;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.LoadList;
import com.dariuszdeoniziak.charades.utils.Optional;

import java.util.List;

public enum CategoriesListState implements State {

    LOADING(
            new Transition() {

                @Override
                public ResultState onEvent(ListLoaded event) {
                    if (event.categories.isEmpty()) {
                        return valid(EMPTY_LIST, Optional.empty(), Optional.empty());
                    } else {
                        return valid(LIST_WITH_ITEMS, Optional.of(event.categories), Optional.empty());
                    }
                }

                @Override
                public ResultState onEvent(Error event) {
                    return valid(ERROR, Optional.empty(), Optional.empty());
                }

            }
    ),
    DELETING(
            new Transition() {
                @Override
                public ResultState onEvent(LoadList event) {
                    return valid(LOADING, Optional.empty(), Optional.empty());
                }

                @Override
                public ResultState onEvent(Error event) {
                    return valid(ERROR, Optional.empty(), Optional.empty());
                }
            }
    ),
    ERROR(
            new Transition() {

                @Override
                public ResultState onEvent(LoadList event) {
                    return valid(LOADING, Optional.empty(), Optional.empty());
                }
            }
    ),
    EMPTY_LIST(
            new Transition() {

                @Override
                public ResultState onEvent(LoadList event) {
                    return valid(LOADING, Optional.empty(), Optional.empty());
                }
            }
    ),
    LIST_WITH_ITEMS(
            new Transition() {
                @Override
                public ResultState onEvent(LoadList event) {
                    return valid(LOADING, Optional.empty(), Optional.empty());
                }

                @Override
                public ResultState onEvent(DeleteCategory event) {
                    return valid(DELETING, Optional.empty(), Optional.of(event.category));
                }
            }
    );

    private final Transition transition;

    static ResultState valid(CategoriesListState state, Optional<List<Category>> categoryList, Optional<Category> deleteCategory) {
        return new ResultState() {
            @Override
            public CategoriesListState state() {
                return state;
            }

            @Override
            public Optional<List<Category>> getCategories() {
                return categoryList;
            }

            @Override
            public Optional<Category> getDeletingCategory() {
                return deleteCategory;
            }
        };
    }

    static ResultState invalid() {
        return new ResultState() {
            @Override
            public CategoriesListState state() {
                return ERROR;
            }

            @Override
            public Optional<List<Category>> getCategories() {
                return Optional.empty();
            }

            @Override
            public Optional<Category> getDeletingCategory() {
                return Optional.empty();
            }
        };
    }

    static ResultState defaultResultState() {
        return new ResultState() {

            @Override
            public CategoriesListState state() {
                return EMPTY_LIST;
            }

            @Override
            public Optional<List<Category>> getCategories() {
                return Optional.empty();
            }

            @Override
            public Optional<Category> getDeletingCategory() {
                return Optional.empty();
            }
        };
    }

    CategoriesListState(
            Transition transition
    ) {
        this.transition = transition;
    }

    @Override
    public ResultState transition(CategoriesListStateMachine.Event event) {
        return event.dispatch(transition);
    }
}
