package com.dariuszdeoniziak.charades.modules;

import com.dariuszdeoniziak.charades.App;
import com.dariuszdeoniziak.charades.coordinators.app.AppCoordinator;
import com.dariuszdeoniziak.charades.coordinators.app.AppCoordinatorImpl;
import com.dariuszdeoniziak.charades.coordinators.categories.CategoriesCoordinator;
import com.dariuszdeoniziak.charades.coordinators.categories.CategoriesCoordinatorImpl;
import com.dariuszdeoniziak.charades.data.datasources.PreferencesDataSource;
import com.dariuszdeoniziak.charades.data.datasources.sharedpreferences.SharedPreferencesDataSource;
import com.dariuszdeoniziak.charades.data.repositories.PreferencesRepository;
import com.dariuszdeoniziak.charades.data.repositories.PreferencesRepositoryImpl;
import com.dariuszdeoniziak.charades.navigators.DestinationFactory;
import com.dariuszdeoniziak.charades.navigators.Navigator;
import com.dariuszdeoniziak.charades.navigators.ScreenNavigator;
import com.dariuszdeoniziak.charades.navigators.ScreenNavigatorHost;
import com.dariuszdeoniziak.charades.navigators.ScreenNavigatorHostMonitor;
import com.dariuszdeoniziak.charades.schedulers.DefaultSchedulerFactory;
import com.dariuszdeoniziak.charades.schedulers.SchedulerFactory;
import com.dariuszdeoniziak.charades.statemachines.coordinator.app.AppCoordinatorStateDispatcher;
import com.dariuszdeoniziak.charades.statemachines.coordinator.app.AppCoordinatorStateMachine;
import com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.DestinationCoordinatorStateMachine;
import com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.DestinationCoordinatorStateMachineDispatcher;
import com.dariuszdeoniziak.charades.utils.Logger;
import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;
import com.dariuszdeoniziak.charades.views.fragments.FragmentDestinationFactory;

import org.codejargon.feather.Provides;

import java.lang.ref.WeakReference;

import javax.inject.Singleton;


public class AppModule {

    private final WeakReference<App> appRef;

    public AppModule(App app) {
        this.appRef = new WeakReference<>(app);
    }

    @Provides
    @Singleton
    public Logger provideLogger() {
        return new Logger();
    }

    @Provides
    @Singleton
    public SchedulerFactory provideSchedulerFactory() {
        return new DefaultSchedulerFactory();
    }

    @Provides
    @Singleton
    public PreferencesDataSource providePreferencesDataSource() {
        return new SharedPreferencesDataSource(appRef.get());
    }

    @Provides
    @Singleton
    public PreferencesRepository providePreferencesRepository() {
        return new PreferencesRepositoryImpl(providePreferencesDataSource());
    }

    @Provides
    @Singleton
    public AppCoordinatorStateMachine provideAppCoordinatorStateMachine(
            AppCoordinatorStateDispatcher appCoordinatorStateDispatcher
    ) {
        return appCoordinatorStateDispatcher;
    }

    @Provides
    @Singleton
    public DestinationCoordinatorStateMachine provideDestinationCoordinatorStateMachine(
            DestinationCoordinatorStateMachineDispatcher destinationCoordinatorStateMachineDispatcher
    ) {
        return destinationCoordinatorStateMachineDispatcher;
    }

    @Provides
    @Singleton
    public DestinationFactory<BaseFragment> provideFragmentDestinationFactory() {
        return new FragmentDestinationFactory();
    }

    @Provides
    @Singleton
    public Navigator.Screen provideScreenNavigator(ScreenNavigator screenNavigator) {
        return screenNavigator;
    }

    @Provides
    @Singleton
    public ScreenNavigatorHost provideScreenNavigatorHost(ScreenNavigator screenNavigator) {
        return screenNavigator;
    }

    @Provides
    @Singleton
    public ScreenNavigatorHostMonitor provideScreenNavigatorHostMonitor(ScreenNavigator screenNavigator) {
        return screenNavigator;
    }

    @Provides
    @Singleton
    public AppCoordinator provideAppCoordinator(AppCoordinatorImpl appCoordinator) {
        return appCoordinator;
    }

    @Provides
    @Singleton
    public CategoriesCoordinator provideCategoriesCoordinator(CategoriesCoordinatorImpl categoriesCoordinator) {
        return categoriesCoordinator;
    }
}
