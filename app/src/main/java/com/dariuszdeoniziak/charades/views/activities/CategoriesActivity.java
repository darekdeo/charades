package com.dariuszdeoniziak.charades.views.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.views.Layout;
import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;
import com.dariuszdeoniziak.charades.views.fragments.CategoryListFragment;
import com.dariuszdeoniziak.charades.presenters.CategoriesActivityPresenter;
import com.dariuszdeoniziak.charades.views.CategoriesView;
import com.dariuszdeoniziak.charades.models.TestClass;
import com.dariuszdeoniziak.charades.utils.AndroidStaticsWrapper;

import javax.inject.Inject;

@Layout(R.layout.activity_categories)
public class CategoriesActivity extends BaseActivity implements CategoriesView {

    private static final String KEY_FRAGMENT = "key_fragment";
    BaseFragment fragment;

    @Inject TestClass testClass;
    @Inject CategoriesActivityPresenter presenter;

    void replace(CategoriesActivityPresenter presenter, AndroidStaticsWrapper androidWrapper) {
        this.presenter = presenter;
        this.androidWrapper = androidWrapper;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragment = getSavedFragment(savedInstanceState, KEY_FRAGMENT);
        if (fragment == null) {
            fragment = new CategoryListFragment();
            replaceFragment(null, fragment, R.id.fragment_container, fragment.TAG);
        }

        Log.i(TAG, "onCreate: " + testClass.say());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onTakeView(this);
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
    public void displayTextInfo(String text) {
        androidWrapper.showToast(this, text, Toast.LENGTH_SHORT);
    }
}
