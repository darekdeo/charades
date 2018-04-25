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
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new CategoriesActivityPresenter(preferencesInteractor);
    }

    @After
    public void tearDown() {
        reset(view, preferencesInteractor);
    }

    @Test
    public void showWelcomeBackMessage() {
        // given
        when(presenter.preferences.isFirstRun()).
                thenReturn(false);

        // when
        presenter.onTakeView(view);

        // then
        verify(view).showTextInfo("Hello again!");
    }

    @Test
    public void doNotShowWelcomeBackMessage() {
        // given
        when(presenter.preferences.isFirstRun()).
                thenReturn(true);

        // when
        presenter.onTakeView(view);

        // then
        verifyZeroInteractions(view);
    }
}