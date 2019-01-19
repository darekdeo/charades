package com.dariuszdeoniziak.charades.views.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.dariuszdeoniziak.charades.modules.ActivityModule;
import com.dariuszdeoniziak.charades.utils.AndroidStaticsWrapper;
import com.dariuszdeoniziak.charades.utils.FontInjector;
import com.dariuszdeoniziak.charades.views.Layout;
import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;

import org.codejargon.feather.Feather;

import javax.inject.Inject;

import trikita.knork.Knork;


public abstract class BaseActivity extends AppCompatActivity {

    public final String TAG;

    @LayoutRes protected int layoutResId;

    Feather feather;
    @Inject AndroidStaticsWrapper androidWrapper;

    public BaseActivity() {
        TAG = this.getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        feather = Feather.with(new ActivityModule(this));
        feather.injectFields(this);

        getAnnotations();
        setContentView(layoutResId);
        Knork.inject(getWindow().getDecorView(), this);
        FontInjector.inject(this);
    }

    private void getAnnotations() {
        Layout layout;
        if ((layout = getClass().getAnnotation(Layout.class)) != null) {
            layoutResId = layout.value();
        }
    }

    @Override
    protected void onDestroy() {
        feather = null;
        Knork.reset(this);
        super.onDestroy();
    }

    public void replaceFragment(@Nullable Bundle args, Fragment fragment, int containerResId, String tag, boolean backstack) {
        Fragment currentFragment = getCurrentFragment(containerResId);

        if (!(currentFragment != null && currentFragment.getTag().equals(tag))) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            if (args != null)
                fragment.setArguments(args);
            transaction.replace(containerResId, fragment, tag);
            if (backstack)
                transaction.addToBackStack(tag);
            transaction.commit();
        }
    }

    public void popFragmentBackStack() {
        FragmentManager manager = getFragmentManager();
        manager.popBackStack();
    }

    public Fragment getCurrentFragment(int containerResId) {
        FragmentManager manager = getFragmentManager();

        Fragment fragment;
        fragment = getFragmentFromBackStack(manager);

        if (fragment != null)
            return fragment;

        return manager.findFragmentById(containerResId);
    }

    private Fragment getFragmentFromBackStack(FragmentManager manager) {
        int count = manager.getBackStackEntryCount();
        if (count > 0) {
            FragmentManager.BackStackEntry entry = manager.getBackStackEntryAt(count - 1);
            return manager.findFragmentByTag(entry.getName());
        }
        return null;
    }

    public BaseFragment getSavedFragment(Bundle bundle, String key) {
        if (bundle != null && !TextUtils.isEmpty(key))
            return (BaseFragment) getFragmentManager().getFragment(bundle, key);
        return null;
    }

    public void saveFragment(BaseFragment fragment, Bundle bundle, String key) {
        if (fragment != null && bundle != null && !TextUtils.isEmpty(key))
            getFragmentManager().putFragment(bundle, key, fragment);
    }
}
