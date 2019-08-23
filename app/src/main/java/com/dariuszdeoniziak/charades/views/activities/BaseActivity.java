package com.dariuszdeoniziak.charades.views.activities;

import android.os.Bundle;
import android.text.TextUtils;

import com.dariuszdeoniziak.charades.modules.ActivityModule;
import com.dariuszdeoniziak.charades.utils.AndroidStaticsWrapper;
import com.dariuszdeoniziak.charades.views.Layout;
import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;

import org.codejargon.feather.Feather;

import javax.inject.Inject;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import trikita.knork.Knork;


public abstract class BaseActivity extends AppCompatActivity {

    public final String TAG;

    @LayoutRes private int layoutResId;

    @Inject AndroidStaticsWrapper androidWrapper;

    public BaseActivity() {
        TAG = this.getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Feather feather = Feather.with(new ActivityModule(this));
        feather.injectFields(this);

        getAnnotations();
        setContentView(layoutResId);
        Knork.inject(getWindow().getDecorView(), this);
    }

    private void getAnnotations() {
        Layout layout;
        if ((layout = getClass().getAnnotation(Layout.class)) != null) {
            layoutResId = layout.value();
        }
    }

    @Override
    protected void onDestroy() {
        Knork.reset(this);
        super.onDestroy();
    }

    public void replaceFragment(@Nullable Bundle args, Fragment fragment, int containerResId, String tag, boolean backstack) {
        Fragment currentFragment = getCurrentFragment(containerResId);

        if (!(currentFragment != null && currentFragment.getTag().equals(tag))) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (args != null)
                fragment.setArguments(args);
            transaction.replace(containerResId, fragment, tag);
            if (backstack)
                transaction.addToBackStack(tag);
            transaction.commit();
        }
    }

    public void popFragmentBackStack() {
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack();
    }

    public Fragment getCurrentFragment(int containerResId) {
        FragmentManager manager = getSupportFragmentManager();

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
            return (BaseFragment) getSupportFragmentManager().getFragment(bundle, key);
        return null;
    }

    public void saveFragment(BaseFragment fragment, Bundle bundle, String key) {
        if (fragment != null && bundle != null && !TextUtils.isEmpty(key))
            getSupportFragmentManager().putFragment(bundle, key, fragment);
    }
}
