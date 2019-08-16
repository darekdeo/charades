package com.dariuszdeoniziak.charades.data.datasources;


public interface PreferencesDataSource {

    void saveFirstRun();
    void deleteFirstRun();
    boolean isFirstRun();
}
