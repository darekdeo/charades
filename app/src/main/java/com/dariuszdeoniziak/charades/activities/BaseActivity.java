package com.dariuszdeoniziak.charades.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.dariuszdeoniziak.charades.activities.fragments.BaseFragment;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    public String TAG = null;

    @LayoutRes
    protected int layoutResId;

    public BaseActivity() {
        TAG = this.getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getAnnotations();
        setContentView(layoutResId);
        ButterKnife.bind(this);
    }

    public void replaceFragment(@Nullable Bundle args, Fragment fragment, int containerResId, String tag) {
        Fragment currentFragment = getCurrentFragment(containerResId);

        if (!(currentFragment != null && currentFragment.getTag().equals(tag))) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            if (args != null)
                fragment.setArguments(args);
            transaction.replace(containerResId, fragment, tag);
            transaction.commit();
        }
    }

    public Fragment getCurrentFragment(int containerResId) {
        FragmentManager manager = getFragmentManager();

        Fragment fragment = null;
        int count = manager.getBackStackEntryCount();
        if (count > 0) {
            FragmentManager.BackStackEntry entry = manager.getBackStackEntryAt(count - 1);
            fragment = manager.findFragmentByTag(entry.getName());
        }

        if (fragment != null)
            return fragment;

        return manager.findFragmentById(containerResId);
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

    protected void getAnnotations() {
        Layout layout;
        if ((layout = getClass().getAnnotation(Layout.class)) != null) {
            layoutResId = layout.value();
        }
    }
}
