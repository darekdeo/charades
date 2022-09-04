package com.dariuszdeoniziak.charades.statemachines;

public interface State<EVENT, RESULT> {

    RESULT transition(EVENT event);
}
