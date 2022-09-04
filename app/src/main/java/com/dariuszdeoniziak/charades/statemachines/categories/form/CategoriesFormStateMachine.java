package com.dariuszdeoniziak.charades.statemachines.categories.form;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.statemachines.categories.form.events.FormLoaded;
import com.dariuszdeoniziak.charades.statemachines.categories.form.events.LoadForm;
import com.dariuszdeoniziak.charades.statemachines.categories.form.events.Error;
import com.dariuszdeoniziak.charades.utils.Optional;

import io.reactivex.rxjava3.core.Observable;

public interface CategoriesFormStateMachine {

    // STATE
    Observable<CategoriesFormStateMachine.ResultState> state();
    interface State extends com.dariuszdeoniziak.charades.statemachines.State<Event, ResultState> {}
    interface ResultState {
        CategoriesFormState state();
        Optional<Category> getCategory();
    }

    // EVENTS
    interface Event extends com.dariuszdeoniziak.charades.statemachines.Event<Transition, ResultState> {}
    void onLoadForm();
    void onFormLoaded(Category category);
    void onLoadingError(Throwable throwable);

    // TRANSITION
    interface Transition {
        default ResultState onEvent(LoadForm event) {
            return CategoriesFormState.invalid();
        }
        default ResultState onEvent(FormLoaded event) {
            return CategoriesFormState.invalid();
        }
        default ResultState onEvent(Error event) {
            return CategoriesFormState.invalid();
        }
    }

}
