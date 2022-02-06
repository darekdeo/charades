package com.dariuszdeoniziak.charades.statemachines;

public interface Event<TRANSITION, STATE> {
    STATE dispatch(TRANSITION transition);
}
