package com.dariuszdeoniziak.charades.statemachines;

public interface State<EVENT> {

    State<EVENT> transition(EVENT event);
}
