package com.dariuszdeoniziak.charades.models.interactors;


public interface PreferencesInteractor {

    void saveFirstRun();
    void deleteFirstRun();
    boolean isFirstRun();
}
