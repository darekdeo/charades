package com.dariuszdeoniziak.charades.statemachines.categories.list;

import static com.dariuszdeoniziak.charades.statemachines.categories.list.CategoriesListStateMachine.Transition;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.statemachines.Event;
import com.dariuszdeoniziak.charades.statemachines.State;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.DeleteCategory;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.DeleteCategoryCancel;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.ListLoaded;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.LoadList;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.LoadingError;

import java.util.List;

public enum CategoriesListState implements State<Event<Transition, CategoriesListState>>, CategoriesListStateMachine.DataReader {

    LOADING(
            new Transition() {

                @Override
                public CategoriesListState onEvent(LoadList event) {
                    return null;
                }

                @Override
                public CategoriesListState onEvent(ListLoaded event) {
                    if (event.categories.isEmpty()) {
                        return CategoriesListState.EMPTY_LIST;
                    } else {
                        CategoriesListState state = CategoriesListState.LIST_WITH_ITEMS;
                        state.categories = event.categories;
                        return state;
                    }
                }

                @Override
                public CategoriesListState onEvent(LoadingError event) {
                    return CategoriesListState.LOADING_ERROR;
                }

                @Override
                public CategoriesListState onEvent(DeleteCategory event) {
                    return null;
                }

                @Override
                public CategoriesListState onEvent(DeleteCategoryCancel event) {
                    return null;
                }
            }
    ),
    LOADING_ERROR(
            new Transition() {

                @Override
                public CategoriesListState onEvent(LoadList event) {
                    return CategoriesListState.LOADING;
                }

                @Override
                public CategoriesListState onEvent(ListLoaded event) {
                    return null;
                }

                @Override
                public CategoriesListState onEvent(LoadingError event) {
                    return null;
                }

                @Override
                public CategoriesListState onEvent(DeleteCategory event) {
                    return null;
                }

                @Override
                public CategoriesListState onEvent(DeleteCategoryCancel event) {
                    return null;
                }
            }
    ),
    EMPTY_LIST(
            new Transition() {

                @Override
                public CategoriesListState onEvent(LoadList event) {
                    return LOADING;
                }

                @Override
                public CategoriesListState onEvent(ListLoaded event) {
                    return null;
                }

                @Override
                public CategoriesListState onEvent(LoadingError event) {
                    return null;
                }

                @Override
                public CategoriesListState onEvent(DeleteCategory event) {
                    return null;
                }

                @Override
                public CategoriesListState onEvent(DeleteCategoryCancel event) {
                    return null;
                }
            }
    ),
    LIST_WITH_ITEMS(
            new Transition() {
                @Override
                public CategoriesListState onEvent(LoadList event) {
                    return LOADING;
                }

                @Override
                public CategoriesListState onEvent(ListLoaded event) {
                    return null;
                }

                @Override
                public CategoriesListState onEvent(LoadingError event) {
                    return null;
                }

                @Override
                public CategoriesListState onEvent(DeleteCategory event) {
                    CategoriesListState state = CategoriesListState.DELETING_CATEGORY;
                    state.deletingCategory = event.category;
                    return state;
                }

                @Override
                public CategoriesListState onEvent(DeleteCategoryCancel event) {
                    return null;
                }
            }
    ),
    DELETING_CATEGORY(
            new CategoriesListStateMachine.Transition() {

                @Override
                public CategoriesListState onEvent(LoadList event) {
                    return LOADING; // Reload list after deleting category.
                }

                @Override
                public CategoriesListState onEvent(ListLoaded event) {
                    return null;
                }

                @Override
                public CategoriesListState onEvent(LoadingError event) {
                    return null;
                }

                @Override
                public CategoriesListState onEvent(DeleteCategory event) {
                    return null;
                }

                @Override
                public CategoriesListState onEvent(DeleteCategoryCancel event) {
                    return LIST_WITH_ITEMS;
                }
            }
    );

    private final Transition transition;

    CategoriesListState(
            Transition transition
    ) {
        this.transition = transition;
    }

    @Override
    public CategoriesListState transition(Event<Transition, CategoriesListState> event) {
        return event.dispatch(transition);
    }

    private List<Category> categories;
    private Category deletingCategory;

    @Override
    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public Category getDeletingCategory() {
        return deletingCategory;
    }
}
