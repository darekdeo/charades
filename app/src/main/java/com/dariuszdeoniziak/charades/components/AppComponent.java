package com.dariuszdeoniziak.charades.components;

import com.dariuszdeoniziak.charades.activities.BaseActivity;
import com.dariuszdeoniziak.charades.modules.AppModule;
import com.dariuszdeoniziak.charades.modules.DbModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DbModule.class})
public interface AppComponent {
    void inject(BaseActivity activity);
}
