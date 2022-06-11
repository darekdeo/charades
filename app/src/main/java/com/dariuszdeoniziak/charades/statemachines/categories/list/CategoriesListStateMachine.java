package com.dariuszdeoniziak.charades.statemachines.categories.list;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.DeleteCategory;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.DeleteCategoryCancel;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.ListLoaded;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.LoadList;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.LoadingError;

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
    void onLoadingError(Throwable throwable);
    void onDeleteCategory(Category category);
    void onDeleteCategoryCancel();

    // TRANSITION
    interface Transition {
        CategoriesListState onEvent(LoadList event);
        CategoriesListState onEvent(ListLoaded event);
        CategoriesListState onEvent(LoadingError event);
        CategoriesListState onEvent(DeleteCategory event);
        CategoriesListState onEvent(DeleteCategoryCancel event);
    }

}