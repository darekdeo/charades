package com.dariuszdeoniziak.charades.models.interactors;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;

public class SharedPreferencesInteractor implements PreferencesInteractor {

    private final String FIRST_RUN = "first_run";

    SharedPreferences preferences;

    @Inject
    public SharedPreferencesInteractor(Context context) {
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
