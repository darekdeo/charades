package com.dariuszdeoniziak.charades.statemachines;

public interface Event<TRANSITION, RESULT> {
    RESULT dispatch(TRANSITION transition);
}
