package com.dariuszdeoniziak.charades.utils;


import android.util.Log;

public class Logger {

    public static void debug(String message) {
        Log.d(tag(), defaults() + message);
    }

    public static void info(String message) {
        Log.i(tag(), defaults() + message);
    }

    public static void error(String message) {
        Log.e(tag(), defaults() + message);
    }

    public static void error(String message, Throwable error) {
        Log.e(tag(), defaults() + message, error);
    }

    private static String tag() {
        return trace().getClassName();
    }

    private static String defaults() {
        return trace().getMethodName() + " (" + trace().getLineNumber() + "): ";
    }

    private static StackTraceElement trace() {
        return Thread.currentThread().getStackTrace()[5];
    }
}
