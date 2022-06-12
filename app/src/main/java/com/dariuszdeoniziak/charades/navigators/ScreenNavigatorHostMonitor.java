package com.dariuszdeoniziak.charades.navigators;

import io.reactivex.rxjava3.core.Observable;

public interface ScreenNavigatorHostMonitor {
    Observable<HostStatus> getStatus();
}
