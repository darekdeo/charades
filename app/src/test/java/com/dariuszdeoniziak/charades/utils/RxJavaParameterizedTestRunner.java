package com.dariuszdeoniziak.charades.utils;

import org.junit.runners.Parameterized;

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxJavaParameterizedTestRunner extends Parameterized {

    public RxJavaParameterizedTestRunner(Class<?> klass) throws Throwable {
        super(klass);

        RxAndroidPlugins.reset();
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }
}
