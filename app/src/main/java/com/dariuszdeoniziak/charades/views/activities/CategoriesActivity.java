package com.dariuszdeoniziak.charades.views.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.models.TestClass;
import com.dariuszdeoniziak.charades.presenters.CategoriesActivityPresenter;
import com.dariuszdeoniziak.charades.utils.AndroidStaticsWrapper;
import com.dariuszdeoniziak.charades.views.CategoriesView;
import com.dariuszdeoniziak.charades.views.Font;
import com.dariuszdeoniziak.charades.views.Layout;
import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;
import com.dariuszdeoniziak.charades.views.fragments.CategoriesFormFragment;
import com.dariuszdeoniziak.charades.views.fragments.CategoriesListFragment;

import javax.inject.Inject;

import trikita.knork.Knork;

@Layout(R.layout.activity_categories)
public class CategoriesActivity extends BaseActivity implements CategoriesView {

    private static final String KEY_FRAGMENT = "key_fragment";
    BaseFragment fragment;

    @Inject TestClass testClass;
    @Inject CategoriesActivityPresenter presenter;

    @Font(path = "fontawesome-webfont.ttf")
    @Knork.Id(R.id.button_plus) TextView buttonPlus;

    void replace(CategoriesActivityPresenter presenter, AndroidStaticsWrapper androidWrapper) {
        this.presenter = presenter;
        this.androidWrapper = androidWrapper;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragment = getSavedFragment(savedInstanceState, KEY_FRAGMENT);
        if (fragment == null) {
            fragment = CategoriesListFragment.newInstance();
            replaceFragment(null, fragment, R.id.fragment_container, fragment.TAG);
        }

        Log.i(TAG, "onCreate: " + testClass.say());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onTakeView(this);

        buttonPlus.setOnClickListener(buttonPlusClickListener);
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

    View.OnClickListener buttonPlusClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            toggleViewMode(null);
        }
    };

    /**
     * Toggle between edit mode and list mode.
     * @param args an args to open certain category to edit.
     */
    public void toggleViewMode(@Nullable Bundle args) {
        if (getCurrentFragment(R.id.fragment_container).getClass() == CategoriesListFragment.class) {
            fragment = CategoriesFormFragment.newInstance();
            replaceFragment(args, fragment, R.id.fragment_container, fragment.TAG);
        } else {
            popFragmentBackStack();
        }
    }
}
