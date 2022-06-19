package com.dariuszdeoniziak.charades.navigators;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import com.dariuszdeoniziak.charades.utils.Optional;
import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class ScreenNavigator implements Navigator.Screen, ScreenNavigatorHost, ScreenNavigatorHostMonitor, LifecycleEventObserver {

    private final BehaviorSubject<HostStatus> hostStatus = BehaviorSubject.createDefault(HostStatus.DETACHED);
    private final DestinationFactory.Fragment fragmentDestinationFactory;
    private @IdRes int containerResId = -1;
    private AppCompatActivity activity = null;

    @Inject
    public ScreenNavigator(
            DestinationFactory.Fragment fragmentDestinationFactory
    ) {
        this.fragmentDestinationFactory = fragmentDestinationFactory;
    }

    @Override
    public void attach(AppCompatActivity activity, @IdRes int containerResId) {
        this.activity = activity;
        this.containerResId = containerResId;
        activity.getLifecycle().addObserver(this);
        hostStatus.onNext(HostStatus.ATTACHED);
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            hostStatus.onNext(HostStatus.DETACHED);
            activity = null;
        }
    }

    @Override
    public Observable<HostStatus> getStatus() {
        return hostStatus.hide();
    }

    @Override
    public Completable navigate(Destination destination) {
        return Single
                .fromCallable(() -> Optional.of(getCurrentFragment(containerResId)))
                .filter(currentFragmentOptional -> !currentFragmentOptional.predicate(value -> destination.getTag().equals(value.getTag())))
                .flatMapSingle((Function<Optional<BaseFragment>, SingleSource<BaseFragment>>) baseFragmentOptional -> fragmentDestinationFactory.create(destination))
                .flatMapCompletable(newFragment -> Completable.fromAction(() -> {
                    FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                    transaction.replace(containerResId, newFragment, destination.getTag());
                    transaction.commitNow();
                }));
    }

    private BaseFragment getCurrentFragment(int containerResId) {
        FragmentManager manager = activity.getSupportFragmentManager();
        return (BaseFragment) manager.findFragmentById(containerResId);
    }

}
