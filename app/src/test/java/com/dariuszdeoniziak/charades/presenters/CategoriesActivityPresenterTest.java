package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.models.interactors.PreferencesInteractor;
import com.dariuszdeoniziak.charades.views.CategoriesView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CategoriesActivityPresenterTest {

    @Mock CategoriesView view;
    @Mock PreferencesInteractor preferencesInteractor;
    CategoriesActivityPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new CategoriesActivityPresenter(view, preferencesInteractor);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void showWelcomeBackMessage() {
        presenter.onTakeView();
        // todo test mock
    }
}