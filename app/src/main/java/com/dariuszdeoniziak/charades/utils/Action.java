package com.dariuszdeoniziak.charades.utils;

public interface Action {
    void invoke();

    Action NONE = () -> {
    };
}
