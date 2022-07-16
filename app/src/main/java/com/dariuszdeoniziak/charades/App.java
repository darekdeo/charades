package com.dariuszdeoniziak.charades;

import android.app.Application;

import com.dariuszdeoniziak.charades.coordinators.app.AppCoordinator;
import com.dariuszdeoniziak.charades.modules.AppModule;
import com.dariuszdeoniziak.charades.modules.MappersModule;
import com.dariuszdeoniziak.charades.utils.Optional;

import org.codejargon.feather.Feather;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.Disposable;


public class App extends Application {

    private static final String TAG = App.class.getName();

    private static App instance = null;

    public Feather feather;

    @Inject
    AppCoordinator appCoordinator;

    private Optional<Disposable> appCoordinatorDisposable = Optional.empty();

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        feather = Feather.with(
                new AppModule(this),
                new MappersModule()
        );
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
        instance = null;
    }

}
