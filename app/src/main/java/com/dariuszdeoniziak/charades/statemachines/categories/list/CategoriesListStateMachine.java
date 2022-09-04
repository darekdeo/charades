package com.dariuszdeoniziak.charades.statemachines.categories.list;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.DeleteCategory;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.Error;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.ListLoaded;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.LoadList;
import com.dariuszdeoniziak.charades.utils.Optional;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface CategoriesListStateMachine {

    // STATE
    Observable<ResultState> state();
    interface State extends com.dariuszdeoniziak.charades.statemachines.State<Event, ResultState> {}
    interface ResultState {
        CategoriesListState state();
        Optional<List<Category>> getCategories();
        Optional<Category> getDeletingCategory();
    }

    // EVENTS
    interface Event extends com.dariuszdeoniziak.charades.statemachines.Event<Transition, ResultState> {}
    void onLoadList();
    void onListLoaded(List<Category> categories);
    void onDelete(Category category);
    void onError(Throwable throwable);

    // TRANSITION
    interface Transition {
        default ResultState onEvent(LoadList event) {
            return CategoriesListState.invalid();
        }
        default ResultState onEvent(ListLoaded event) {
            return CategoriesListState.invalid();
        }
        default ResultState onEvent(DeleteCategory event) {
            return CategoriesListState.invalid();
        }
        default ResultState onEvent(Error event) {
            return CategoriesListState.invalid();
        }
    }

}
