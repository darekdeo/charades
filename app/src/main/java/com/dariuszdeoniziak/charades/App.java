package com.dariuszdeoniziak.charades;

import android.app.Application;

import com.dariuszdeoniziak.charades.coordinators.Coordinator;
import com.dariuszdeoniziak.charades.coordinators.app.AppResult;
import com.dariuszdeoniziak.charades.modules.AppModule;
import com.dariuszdeoniziak.charades.utils.Optional;

import org.codejargon.feather.Feather;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.Disposable;


public class App extends Application {

    private static final String TAG = App.class.getName();

    private final static App instance = null;

    private Feather feather;

    @Inject
    Coordinator<AppResult> appCoordinator;

    private Optional<Disposable> appCoordinatorDisposable = Optional.empty();

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        feather = Feather.with(new AppModule(this));
        feather.injectFields(this);

        appCoordinatorDisposable = Optional.of(
                appCoordinator
                        .coordinate()
                        .subscribe(
                                result -> {},
                                throwable -> {}
                        )
        );
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        appCoordinatorDisposable.ifPresent(Disposable::dispose);
        appCoordinatorDisposable = Optional.empty();
        feather = null;
    }
}
