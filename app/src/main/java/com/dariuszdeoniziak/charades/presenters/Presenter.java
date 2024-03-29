package com.dariuszdeoniziak.charades.presenters;


public interface Presenter<V> {

    void onSave(); // called during on saveInstanceState

    void onTakeView(V view); // called during onStart

    void onDropView(); // called during onStop
}
