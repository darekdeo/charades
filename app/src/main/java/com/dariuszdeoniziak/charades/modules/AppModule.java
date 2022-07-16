package com.dariuszdeoniziak.charades.modules;

import androidx.room.Room;

import com.dariuszdeoniziak.charades.App;
import com.dariuszdeoniziak.charades.coordinators.app.AppCoordinator;
import com.dariuszdeoniziak.charades.coordinators.app.AppCoordinatorImpl;
import com.dariuszdeoniziak.charades.coordinators.categories.CategoriesCoordinator;
import com.dariuszdeoniziak.charades.coordinators.categories.CategoriesCoordinatorImpl;
import com.dariuszdeoniziak.charades.data.datasources.CharadesDataSource;
import com.dariuszdeoniziak.charades.data.datasources.LabelsDataSource;
import com.dariuszdeoniziak.charades.data.datasources.PreferencesDataSource;
import com.dariuszdeoniziak.charades.data.datasources.resourcelabels.ResourcesLabelsDataSource;
import com.dariuszdeoniziak.charades.data.datasources.room.CharadesRoomDataSource;
import com.dariuszdeoniziak.charades.data.datasources.room.CharadesRoomDatabase;
import com.dariuszdeoniziak.charades.data.datasources.sharedpreferences.SharedPreferencesDataSource;
import com.dariuszdeoniziak.charades.data.models.room.mappers.FromCategoryRoomModelMapper;
import com.dariuszdeoniziak.charades.data.models.room.mappers.FromCharadeRoomModelMapper;
import com.dariuszdeoniziak.charades.data.models.room.mappers.ToCategoryRoomModelMapper;
import com.dariuszdeoniziak.charades.data.models.room.mappers.ToCharadeRoomModelMapper;
import com.dariuszdeoniziak.charades.data.repositories.CharadesRepository;
import com.dariuszdeoniziak.charades.data.repositories.CharadesRepositoryImpl;
import com.dariuszdeoniziak.charades.data.repositories.LabelsRepository;
import com.dariuszdeoniziak.charades.data.repositories.LabelsRepositoryImpl;
import com.dariuszdeoniziak.charades.data.repositories.PreferencesRepository;
import com.dariuszdeoniziak.charades.data.repositories.PreferencesRepositoryImpl;
import com.dariuszdeoniziak.charades.navigators.DestinationFactory;
import com.dariuszdeoniziak.charades.navigators.DestinationFactoryImpl;
import com.dariuszdeoniziak.charades.navigators.DestinationParser;
import com.dariuszdeoniziak.charades.navigators.Navigator;
import com.dariuszdeoniziak.charades.navigators.ScreenNavigator;
import com.dariuszdeoniziak.charades.navigators.ScreenNavigatorHost;
import com.dariuszdeoniziak.charades.navigators.ScreenNavigatorHostMonitor;
import com.dariuszdeoniziak.charades.presenters.CategoriesFormPresenter;
import com.dariuszdeoniziak.charades.presenters.CategoriesListPresenter;
import com.dariuszdeoniziak.charades.schedulers.DefaultSchedulerFactory;
import com.dariuszdeoniziak.charades.schedulers.SchedulerFactory;
import com.dariuszdeoniziak.charades.statemachines.categories.list.CategoriesListStateMachine;
import com.dariuszdeoniziak.charades.statemachines.categories.list.CategoriesListStateMachineDispatcher;
import com.dariuszdeoniziak.charades.statemachines.coordinator.app.AppCoordinatorStateDispatcher;
import com.dariuszdeoniziak.charades.statemachines.coordinator.app.AppCoordinatorStateMachine;
import com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.DestinationCoordinatorStateMachine;
import com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.DestinationCoordinatorStateMachineDispatcher;
import com.dariuszdeoniziak.charades.utils.Logger;
import com.dariuszdeoniziak.charades.views.CategoriesFormContract;
import com.dariuszdeoniziak.charades.views.CategoriesListContract;
import com.dariuszdeoniziak.charades.views.fragments.CategoriesFormFragment;
import com.dariuszdeoniziak.charades.views.fragments.CategoriesListFragment;
import com.dariuszdeoniziak.charades.views.fragments.FragmentDestinationParser;

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
        return new PreferencesRepositoryImpl(
                appRef.get().feather.provider(PreferencesDataSource.class).get()
        );
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
    public DestinationFactory provideDestinationFactory(DestinationFactoryImpl factory) {
        return factory;
    }

    @Provides
    @Singleton
    public DestinationParser.Fragment provideFragmentDestinationParser(FragmentDestinationParser parser) {
        return parser;
    }

    @Provides
    @Singleton
    public ScreenNavigator provideScreenNavigatorImpl() {
        return new ScreenNavigator(
                appRef.get().feather.provider(DestinationParser.Fragment.class).get()
        );
    }

    @Provides
    @Singleton
    public Navigator.Screen provideScreenNavigator() {
        return appRef.get().feather.provider(ScreenNavigator.class).get();
    }

    @Provides
    @Singleton
    public ScreenNavigatorHost provideScreenNavigatorHost() {
        return appRef.get().feather.provider(ScreenNavigator.class).get();
    }

    @Provides
    @Singleton
    public ScreenNavigatorHostMonitor provideScreenNavigatorHostMonitor() {
        return appRef.get().feather.provider(ScreenNavigator.class).get();
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

    @Provides
    @Singleton
    CategoriesListContract.View provideCategoriesListView(CategoriesListFragment fragment) {
        return fragment;
    }

    @Provides
    @Singleton
    CategoriesListContract.Presenter provideCategoriesListPresenter(CategoriesListPresenter presenter) {
        return presenter;
    }

    @Provides
    @Singleton
    CategoriesListContract.ListItemPresenter provideCategoriesListItemPresenter() {
        return appRef.get().feather.provider(CategoriesListContract.Presenter.class).get();
    }

    @Provides
    @Singleton
    CategoriesFormContract.View provideCategoriesFormView(CategoriesFormFragment fragment) {
        return fragment;
    }

    @Provides
    @Singleton
    CategoriesFormContract.Presenter provideCategoriesFormPresenter(CategoriesFormPresenter presenter) {
        return presenter;
    }

    @Provides
    @Singleton
    CategoriesFormContract.CharadeListItemPresenter provideCharadeListItemPresenter() {
        return appRef.get().feather.provider(CategoriesFormContract.Presenter.class).get();
    }

    @Provides
    @Singleton
    public CategoriesListStateMachine provideCategoriesListStateMachine() {
        return new CategoriesListStateMachineDispatcher(
                new Logger()
        );
    }

    @Provides
    @Singleton
    public CharadesDataSource provideCharadesDataSource() {
        return new CharadesRoomDataSource(Room
                .databaseBuilder(
                        appRef.get().getApplicationContext(),
                        CharadesRoomDatabase.class,
                        "charades.db")
                .allowMainThreadQueries()
                .build());
    }

    @Provides
    @Singleton
    public CharadesRepository provideCharadesRepository() {
        return new CharadesRepositoryImpl(
                provideCharadesDataSource(),
                new ToCategoryRoomModelMapper(),
                new FromCategoryRoomModelMapper(),
                new ToCharadeRoomModelMapper(),
                new FromCharadeRoomModelMapper()
        );
    }

    @Provides
    @Singleton
    public LabelsDataSource provideResourcesLabelsDataSource() {
        return new ResourcesLabelsDataSource(appRef.get().getApplicationContext());
    }

    @Provides
    @Singleton
    public LabelsRepository provideLabelsRepository() {
        return new LabelsRepositoryImpl(provideResourcesLabelsDataSource());
    }

}
