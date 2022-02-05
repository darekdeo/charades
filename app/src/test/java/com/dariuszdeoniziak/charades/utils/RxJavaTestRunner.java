package com.dariuszdeoniziak.charades.utils;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxJavaTestRunner extends BlockJUnit4ClassRunner {

    public RxJavaTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);

        RxAndroidPlugins.reset();
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }
}
