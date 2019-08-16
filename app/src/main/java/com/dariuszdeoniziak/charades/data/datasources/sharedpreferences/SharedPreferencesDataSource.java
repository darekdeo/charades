package com.dariuszdeoniziak.charades.data.datasources.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.dariuszdeoniziak.charades.data.datasources.PreferencesDataSource;

import javax.inject.Inject;


public class SharedPreferencesDataSource implements PreferencesDataSource {

    private final String FIRST_RUN = "first_run";

    private final SharedPreferences preferences;

    @Inject
    public SharedPreferencesDataSource(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public void saveFirstRun() {
        preferences.edit().putBoolean(FIRST_RUN, false).apply();
    }

    @Override
    public boolean isFirstRun() {
        return preferences.getBoolean(FIRST_RUN, true);
    }

    @Override
    public void deleteFirstRun() {
        preferences.edit().remove(FIRST_RUN).apply();
    }
}
