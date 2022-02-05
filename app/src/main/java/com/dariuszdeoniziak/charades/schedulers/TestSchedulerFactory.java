package com.dariuszdeoniziak.charades.schedulers;


import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TestSchedulerFactory implements SchedulerFactory {

    private Scheduler uiScheduler = Schedulers.trampoline();
    private Scheduler ioScheduler = Schedulers.trampoline();
    private Scheduler computationScheduler = Schedulers.trampoline();
    private Scheduler newThreadScheduler = Schedulers.trampoline();

    public void replaceUiScheduler(Scheduler scheduler) {
        uiScheduler = scheduler;
    }

    public void replaceIoScheduler(Scheduler scheduler) {
        ioScheduler = scheduler;
    }

    public void replaceComputationScheduler(Scheduler scheduler) {
        computationScheduler = scheduler;
    }

    public void replaceNewThreadScheduler(Scheduler scheduler) {
        newThreadScheduler = scheduler;
    }

    public void reset() {
        uiScheduler = Schedulers.trampoline();
        ioScheduler = Schedulers.trampoline();
        computationScheduler = Schedulers.trampoline();
        newThreadScheduler = Schedulers.trampoline();
    }

    @Override
    public Scheduler ui() {
        return uiScheduler;
    }

    @Override
    public Scheduler io() {
        return ioScheduler;
    }

    @Override
    public Scheduler computation() {
        return computationScheduler;
    }

    @Override
    public Scheduler newThread() {
        return newThreadScheduler;
    }
}
