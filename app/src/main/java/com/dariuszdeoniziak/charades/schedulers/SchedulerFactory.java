package com.dariuszdeoniziak.charades.schedulers;

import io.reactivex.Scheduler;

public interface SchedulerFactory {

    Scheduler ui();

    Scheduler io();

    Scheduler computation();

    Scheduler newThread();
}
