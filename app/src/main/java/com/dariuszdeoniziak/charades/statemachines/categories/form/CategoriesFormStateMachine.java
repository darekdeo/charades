package com.dariuszdeoniziak.charades.statemachines.categories.form;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.statemachines.categories.form.events.FormLoaded;
import com.dariuszdeoniziak.charades.statemachines.categories.form.events.LoadForm;
import com.dariuszdeoniziak.charades.statemachines.categories.form.events.LoadingError;

import io.reactivex.rxjava3.core.Observable;

public interface CategoriesFormStateMachine {

    // STATE
    Observable<CategoriesFormState> state();
    interface DataReader {
        Category getCategory();
    }

    // EVENTS
    void onLoadForm();
    void onFormLoaded(Category category);
    void onLoadingError(Throwable throwable);

    // TRANSITION
    interface Transition {
        CategoriesFormState onEvent(LoadForm event);
        CategoriesFormState onEvent(FormLoaded event);
        CategoriesFormState onEvent(LoadingError event);
    }

}
