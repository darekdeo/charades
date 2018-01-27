package com.dariuszdeoniziak.charades.utils;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

public class RxJavaTestRunner extends BlockJUnit4ClassRunner {

    public RxJavaTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);

        RxAndroidPlugins.reset();
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }
}
