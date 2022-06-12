package com.dariuszdeoniziak.charades.navigators;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

public interface ScreenNavigatorHost {

    void attach(
            AppCompatActivity activity,
            @IdRes int containerResId
    );

}
