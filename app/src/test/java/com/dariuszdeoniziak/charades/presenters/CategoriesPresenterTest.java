package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.data.repositories.PreferencesRepository;
import com.dariuszdeoniziak.charades.views.CategoriesView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Single;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class CategoriesPresenterTest {

    @Mock CategoriesView view;
    @Mock PreferencesRepository preferencesRepository;
    private CategoriesPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new CategoriesPresenter(preferencesRepository);
    }

    @After
    public void tearDown() {
        reset(view, preferencesRepository);
    }

    @Test
    public void showWelcomeBackMessage() {
        // given
        when(presenter.preferences.isFirstRun()).
                thenReturn(Single.just(false));

        // when
        presenter.onTakeView(view);

        // then
        verify(view).showTextInfo("Hello again!");
    }

    @Test
    public void doNotShowWelcomeBackMessage() {
        // given
        when(presenter.preferences.isFirstRun()).
                thenReturn(Single.just(true));

        // when
        presenter.onTakeView(view);

        // then
        verifyZeroInteractions(view);
    }
}