package com.dariuszdeoniziak.charades.schedulers;


import io.reactivex.rxjava3.core.Scheduler;

public interface SchedulerFactory {

    Scheduler ui();

    Scheduler io();

    Scheduler computation();

    Scheduler newThread();
}
