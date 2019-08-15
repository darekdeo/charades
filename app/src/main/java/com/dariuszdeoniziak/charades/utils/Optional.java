package com.dariuszdeoniziak.charades.utils;

public class Optional<T> {

    private final T value;

    private Optional() {
        this.value = null;
    }

    private Optional(T value) {
        this.value = value;
    }

    public static <T> Optional<T> empty() {
        return new Optional<>();
    }

    public static <T> Optional<T> of(T value) {
        return new Optional<>(value);
    }

    public void ifPresent(Action<T> action) {
        if (value != null) {
            action.apply(value);
        }
    }

    public interface Action<T> {
        void apply(T value);
    }
}
