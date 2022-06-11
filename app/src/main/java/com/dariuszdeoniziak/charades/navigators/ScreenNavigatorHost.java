package com.dariuszdeoniziak.charades.navigators;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.core.Observable;

public interface ScreenNavigatorHost {

    void attach(
            AppCompatActivity activity,
            @IdRes int containerResId
    );

    interface Monitor {
        Observable<HostStatus> getStatus();
    }
}
