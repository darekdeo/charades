package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.models.interactors.PreferencesInteractor;
import com.dariuszdeoniziak.charades.views.CategoriesView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class CategoriesActivityPresenterTest {

    @Mock CategoriesView view;
    @Mock PreferencesInteractor preferencesInteractor;
    CategoriesActivityPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new CategoriesActivityPresenter(preferencesInteractor);
    }

    @After
    public void tearDown() throws Exception {
        reset(view, preferencesInteractor);
    }

    @Test
    public void showWelcomeBackMessage() {
        when(presenter.preferences.isFirstRun()).
                thenReturn(false);
        presenter.onTakeView(view);
        verify(view).displayTextInfo("Hello again!");

        when(presenter.preferences.isFirstRun()).
                thenReturn(true);
        presenter.onTakeView(view);
        verifyZeroInteractions(view);
    }
}