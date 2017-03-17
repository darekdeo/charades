package com.dariuszdeoniziak.charades.presenters;

public interface Presenter<V> {

    public abstract void onSave(); // called during on saveInstanceState
    public abstract void onTakeView(V view); // called during onResume
    public abstract void onDropView(); // called during onDestroy
}
