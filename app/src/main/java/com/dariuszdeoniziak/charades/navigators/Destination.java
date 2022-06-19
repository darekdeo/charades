package com.dariuszdeoniziak.charades.navigators;

import com.dariuszdeoniziak.charades.presenters.Presenter;
import com.dariuszdeoniziak.charades.views.View;

public interface Destination<P extends Presenter<? extends View>> {
    String getTag();
    View getView();
    P getPresenter();
}
