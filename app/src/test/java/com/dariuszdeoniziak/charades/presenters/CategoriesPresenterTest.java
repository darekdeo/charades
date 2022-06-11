package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.data.repositories.PreferencesRepository;
import com.dariuszdeoniziak.charades.views.CategoriesView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.reactivex.rxjava3.core.Single;

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
        when(preferencesRepository.isFirstRun()).
                thenReturn(Single.just(false));

        // when
        presenter.onTakeView(view);

        // then
        verify(view).showTextInfo("Hello again!");
    }

    @Test
    public void doNotShowWelcomeBackMessage() {
        // given
        when(preferencesRepository.isFirstRun()).thenReturn(Single.just(true));

        // when
        presenter.onTakeView(view);

        // then
        verify(view, never()).showTextInfo(anyString());
    }

    @Test
    public void onTakeViewShouldFirstTimeDisplayList() {
        // given
        when(preferencesRepository.isFirstRun()).thenReturn(Single.just(true));
        when(view.getCurrentScreen()).thenReturn(CategoryScreen.NONE);

        // when
        presenter.onTakeView(view);

        // then
        verify(view).toList();
    }

    @Test
    public void onToggleViewModeFromListToForm() {
        // given
        when(preferencesRepository.isFirstRun()).thenReturn(Single.just(true));
        when(view.getCurrentScreen()).thenReturn(CategoryScreen.LIST);

        // when
        presenter.onTakeView(view);
        presenter.onToggleViewMode();

        // then
        verify(view).toForm();
    }

    @Test
    public void onToggleViewModeFromFormToList() {
        // given
        when(preferencesRepository.isFirstRun()).thenReturn(Single.just(true));
        when(view.getCurrentScreen()).thenReturn(CategoryScreen.FORM);

        // when
        presenter.onTakeView(view);
        presenter.onToggleViewMode();

        // then
        verify(view).toList();
    }
}