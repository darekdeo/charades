package com.dariuszdeoniziak.charades.navigators;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class ScreenNavigator implements Navigator, ScreenNavigatorHost, ScreenNavigatorHost.Monitor, LifecycleEventObserver {

    private final BehaviorSubject<HostStatus> hostStatus = BehaviorSubject.createDefault(HostStatus.DETACHED);
    private final DestinationFactory<BaseFragment> fragmentDestinationFactory;
    private @IdRes int containerResId = -1;
    private AppCompatActivity activity = null;

    public ScreenNavigator(
            DestinationFactory<BaseFragment> fragmentDestinationFactory
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
        return Completable.fromAction(() -> {
            Fragment currentFragment = getCurrentFragment(containerResId);
            String tag = destination.getTag();

            if (!(currentFragment != null && currentFragment.getTag() != null && currentFragment.getTag().equals(tag))) {
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                transaction.replace(containerResId, fragmentDestinationFactory.create(destination), tag);
                transaction.commitNow();
            }
        });
    }

    private BaseFragment getCurrentFragment(int containerResId) {
        FragmentManager manager = activity.getSupportFragmentManager();
        return (BaseFragment) manager.findFragmentById(containerResId);
    }

}
