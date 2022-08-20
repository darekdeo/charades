package com.dariuszdeoniziak.charades.statemachines.categories.list;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.DeleteCategory;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.ListLoaded;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.LoadList;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.Error;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface CategoriesListStateMachine {

    // STATE
    Observable<CategoriesListState> state();
    interface DataReader {
        List<Category> getCategories();
        Category getDeletingCategory();
    }

    // EVENTS
    void onLoadList();
    void onListLoaded(List<Category> categories);
    void onDelete(Category category);
    void onError(Throwable throwable);

    // TRANSITION
    interface Transition {
        CategoriesListState onEvent(LoadList event);
        CategoriesListState onEvent(ListLoaded event);
        CategoriesListState onEvent(DeleteCategory event);
        CategoriesListState onEvent(Error event);
    }

}
