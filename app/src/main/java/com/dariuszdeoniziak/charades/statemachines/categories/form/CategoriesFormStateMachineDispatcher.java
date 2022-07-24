package com.dariuszdeoniziak.charades.statemachines.categories.form;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.statemachines.Event;
import com.dariuszdeoniziak.charades.statemachines.categories.form.events.FormLoaded;
import com.dariuszdeoniziak.charades.statemachines.categories.form.events.LoadForm;
import com.dariuszdeoniziak.charades.statemachines.categories.form.events.LoadingError;
import com.dariuszdeoniziak.charades.utils.Logger;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

public class CategoriesFormStateMachineDispatcher implements CategoriesFormStateMachine {

    private final Subject<Event<Transition, CategoriesFormState>> eventStream;
    private final Observable<CategoriesFormState> state;

    public CategoriesFormStateMachineDispatcher(
            Logger logger
    ) {
        eventStream = PublishSubject.create();
        state = eventStream
                .scan(CategoriesFormState.LOADING, (currentState, event) -> {
                    logger.info("State: " + currentState + ", received Event: " + event);
                    return currentState.transition(event);
                });
    }

    @Override
    public Observable<CategoriesFormState> state() {
        return state;
    }

    @Override
    public void onLoadForm() {
        eventStream.onNext(LoadForm.INSTANCE);
    }

    @Override
    public void onFormLoaded(Category category) {
        eventStream.onNext(new FormLoaded(category));
    }

    @Override
    public void onLoadingError(Throwable throwable) {
        eventStream.onNext(new LoadingError(throwable));
    }

}
