package com.dariuszdeoniziak.charades.views.activities;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.databinding.ActivityMainNavigationBinding;
import com.dariuszdeoniziak.charades.navigators.ScreenNavigatorHost;
import com.dariuszdeoniziak.charades.utils.Logger;

import javax.inject.Inject;


public class MainNavigationActivity extends BaseActivity {

    @Inject
    ScreenNavigatorHost screenNavigatorHost;
    @Inject Logger log;

    ActivityMainNavigationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_navigation);
        screenNavigatorHost.attach(this, R.id.screen_container);
    }
}
