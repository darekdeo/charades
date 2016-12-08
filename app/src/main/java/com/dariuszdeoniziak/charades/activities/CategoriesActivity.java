package com.dariuszdeoniziak.charades.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.activities.fragments.BaseFragment;
import com.dariuszdeoniziak.charades.activities.fragments.CategoriesFragment;
import com.dariuszdeoniziak.charades.activities.presenters.CategoriesPresenter;
import com.dariuszdeoniziak.charades.activities.views.CategoriesView;
import com.dariuszdeoniziak.charades.models.TestClass;

import javax.inject.Inject;

@Layout(R.layout.activity_categories)
public class CategoriesActivity extends BaseActivity implements CategoriesView {

    private static final String KEY_FRAGMENT = "key_fragment";
    BaseFragment fragment;

    @Inject CategoriesPresenter presenter;
    @Inject SharedPreferences sharedPreferences;
    @Inject TestClass testClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragment = getSavedFragment(savedInstanceState, KEY_FRAGMENT);
        if (fragment == null) {
            fragment = new CategoriesFragment();
            replaceFragment(null, fragment, R.id.fragment_container, fragment.TAG);
        }

        Log.i(TAG, "onCreate: " + testClass.say());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onTakeView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSave();
        saveFragment(fragment, outState, KEY_FRAGMENT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDropView();
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
