package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.data.repositories.CharadesRepository;
import com.dariuszdeoniziak.charades.utils.RxJavaTestRunner;
import com.dariuszdeoniziak.charades.views.CategoriesListView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(RxJavaTestRunner.class)
public class CategoriesListPresenterTest {

    @Mock List<Category> categories;
    @Mock CategoriesListView view;
    @Mock CharadesRepository charadesRepository;
    private CategoriesListPresenter presenter;

    @Before
    public void setUp() {
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());

        MockitoAnnotations.initMocks(this);
        presenter = new CategoriesListPresenter(charadesRepository);
        presenter.onTakeView(view);
    }

    @After
    public void tearDown() {
        RxJavaPlugins.reset();
        reset(categories, view, charadesRepository);
    }

    @Test
    public void loadCategoriesCallsShowCategories() {
        // given
        when(presenter.charadesRepository.getCategories()).thenReturn(Single.just(categories));

        // when
        presenter.loadCategories();

        // then
        verify(charadesRepository).getCategories();
        verify(view).showProgressIndicator();
        verify(view).showCategories(categories);
        verify(view).hideProgressIndicator();
    }

    @Test
    public void loadCategoriesCallsShowEmptyList() {
        // given
        when(presenter.charadesRepository.getCategories()).thenReturn(Single.just(Collections.emptyList()));

        // when
        presenter.loadCategories();

        // then
        verify(charadesRepository).getCategories();
        verify(view).showProgressIndicator();
        verify(view).showEmptyList();
        verify(view).hideProgressIndicator();
    }
}