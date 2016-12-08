package com.dariuszdeoniziak.charades.activities.presenters;

public abstract class BasePresenter {
    public abstract void onSave(); // called during on saveInstanceState
    public abstract void onTakeView(); // called during onResume
    public abstract void onDropView(); // called during onDestroy
}
