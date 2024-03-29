package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.utils.Optional;

import androidx.annotation.CallSuper;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;


public abstract class AbstractPresenter<V> implements Presenter<V> {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    Optional<V> view = Optional.empty();

    @Override
    public void onSave() {
    }

    @CallSuper
    @Override
    public void onTakeView(V view) {
        this.view = Optional.of(view);
    }

    @CallSuper
    @Override
    public void onDropView() {
        compositeDisposable.clear();
        view = Optional.empty();
    }

    final void run(Operation operation) {
        compositeDisposable.add(operation.invoke());
    }

    interface Operation {
        Disposable invoke();
    }
}
