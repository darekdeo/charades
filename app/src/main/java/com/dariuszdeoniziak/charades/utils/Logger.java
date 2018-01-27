package com.dariuszdeoniziak.charades.utils;


import android.util.Log;

public class Logger {

    public void debug(String message) {
        Log.d(tag(), message); }

    public void info(String message) {
        Log.i(tag(), message); }

    public void error(String message) {
        Log.e(tag(), message); }

    public void error(String message, Throwable error) {
        Log.e(tag(), message, error); }

    private String tag() {
        int CALLER = 5;
        return trace(CALLER).getClassName(); }

    private StackTraceElement trace(int index) {
        return Thread.currentThread().getStackTrace()[index]; }
}
