package com.dariuszdeoniziak.charades.views.activities;

import android.os.Bundle;

import com.dariuszdeoniziak.charades.modules.ActivityModule;
import com.dariuszdeoniziak.charades.modules.MappersModule;
import com.dariuszdeoniziak.charades.views.ComponentsFacade;
import com.dariuszdeoniziak.charades.views.Layout;
import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;

import org.codejargon.feather.Feather;

import javax.inject.Inject;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import trikita.knork.Knork;


public abstract class BaseActivity extends AppCompatActivity {

    @LayoutRes private int layoutResId;

    @Inject ComponentsFacade componentsFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Feather feather = Feather.with(new ActivityModule(this), new MappersModule());
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

    public void replaceFragment(CreateFragment createFragment, String tag, int containerResId, boolean backstack) {
        Fragment currentFragment = getCurrentFragment(containerResId);

        if (!(currentFragment != null && currentFragment.getTag() != null && currentFragment.getTag().equals(tag))) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(containerResId, createFragment.create(), tag);
            if (backstack)
                transaction.addToBackStack(tag);
            transaction.commit();
        }
    }

    public void popOrReplaceFragment(CreateFragment createFragment, String tag, int containerResId) {
        FragmentManager manager = getSupportFragmentManager();

        Fragment fragmentByTag = manager.findFragmentByTag(tag);

        if (fragmentByTag != null) {
            if (!manager.popBackStackImmediate(tag, 0))
                manager.popBackStack();
        } else {
            replaceFragment(createFragment, tag, containerResId, false);
        }
    }

    public BaseFragment getCurrentFragment(int containerResId) {
        FragmentManager manager = getSupportFragmentManager();

        BaseFragment fragment = getFragmentFromBackStack(manager);

        if (fragment != null)
            return fragment;

        return (BaseFragment) manager.findFragmentById(containerResId);
    }

    private BaseFragment getFragmentFromBackStack(FragmentManager manager) {
        int count = manager.getBackStackEntryCount();
        if (count > 0) {
            FragmentManager.BackStackEntry entry = manager.getBackStackEntryAt(count - 1);
            return (BaseFragment) manager.findFragmentByTag(entry.getName());
        }
        return null;
    }

    interface CreateFragment {
        BaseFragment create();
    }
}
