package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.models.Category;
import com.dariuszdeoniziak.charades.models.interactors.ModelInteractor;
import com.dariuszdeoniziak.charades.utils.RxJavaTestRunner;
import com.dariuszdeoniziak.charades.views.CategoryListView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RxJavaTestRunner.class)
public class CategoryListPresenterTest {

    @Mock List<Category> categories;
    @Mock CategoryListView view;
    @Mock ModelInteractor modelInteractor;
    CategoryListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });

        MockitoAnnotations.initMocks(this);
        presenter = new CategoryListPresenter(modelInteractor);
        presenter.onTakeView(view);
    }

    @After
    public void tearDown() throws Exception {
        RxJavaPlugins.reset();
    }

    @Test
    public void loadCategoriesCallsShowCategories() {
        when(presenter.modelInteractor.getCategories())
                .thenReturn(categories);
        presenter.loadCategories();
        verify(modelInteractor).getCategories();
        verify(view).showProgressIndicator();
        verify(view).showCategories(categories);
    }

    @Test
    public void loadCategoriesCallsShowEmptyList() throws InterruptedException {
        when(presenter.modelInteractor.getCategories())
                .thenReturn(Collections.<Category>emptyList());
        presenter.loadCategories();
        verify(modelInteractor).getCategories();
        verify(view).showProgressIndicator();
        verify(view).showEmptyList();
    }
}