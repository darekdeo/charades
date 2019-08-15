package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.utils.Optional;

import androidx.annotation.CallSuper;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public abstract class AbstractPresenter<V> implements Presenter<V> {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    protected Optional<V> view = Optional.empty();

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

    public interface Operation {
        Disposable invoke();
    }
}
