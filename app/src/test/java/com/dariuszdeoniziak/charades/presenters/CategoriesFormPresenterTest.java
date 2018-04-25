package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.models.Category;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RxJavaTestRunner.class)
public class CategoriesFormPresenterTest {

    @Mock CategoriesFormView view;
    @Mock ModelInteractor modelInteractor;
    CategoriesFormPresenter presenter;

    @Before
    public void setUp() {
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());

        MockitoAnnotations.initMocks(this);
        presenter = new CategoriesFormPresenter(modelInteractor);
        presenter.onTakeView(view);
    }

    @After
    public void tearDown() {
        RxJavaPlugins.reset();
        reset(view, modelInteractor);
    }

    @Test
    public void loadCategory() {
        // given
        Long categoryId = 1L;
        Category category = Category.builder()
                .id(categoryId)
                .build();
        when(modelInteractor.getCategory(categoryId)).thenReturn(category);

        // when
        presenter.loadCategory(categoryId);

        // then
        verify(modelInteractor).getCategory(categoryId);
        assertNotNull(presenter.category);
        assertEquals(categoryId, presenter.category.getId());
        verify(view).showCategory(category);
    }

    @Test
    public void loadNullCategory() {
        // given
        Long categoryId = 1L;
        when(modelInteractor.getCategory(categoryId)).thenReturn(null);

        // when
        presenter.loadCategory(categoryId);

        // then
        verify(modelInteractor).getCategory(categoryId);
        assertNotNull(presenter.category);
        assertNotEquals(categoryId, presenter.category.getId());
        verify(view, never()).showCategory(presenter.category);
    }

    @Test
    public void saveCategoryTitle() {
        // given
        String categoryName = "test_category_title";

        // when
        presenter.saveCategoryTitle(categoryName);

        // then
        assertNotNull(presenter.category);
        assertEquals(categoryName, presenter.category.getName());
        verify(modelInteractor).saveCategory(presenter.category);
    }
}