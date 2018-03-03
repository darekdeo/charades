package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.models.interactors.ModelInteractor;
import com.dariuszdeoniziak.charades.utils.RxJavaTestRunner;
import com.dariuszdeoniziak.charades.views.CategoriesFormView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.reset;

@RunWith(RxJavaTestRunner.class)
public class CategoriesFormPresenterTest {

    @Mock CategoriesFormView view;
    @Mock ModelInteractor modelInteractor;
    CategoriesFormPresenter presenter;

    @Before
    public void setUp() throws Exception {
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());

        MockitoAnnotations.initMocks(this);
        presenter = new CategoriesFormPresenter(modelInteractor);
        presenter.onTakeView(view);
    }

    @After
    public void tearDown() throws Exception {
        RxJavaPlugins.reset();
        reset(view, modelInteractor);
    }

    @Test
    public void onTitleEdited() throws Exception {

    }
}