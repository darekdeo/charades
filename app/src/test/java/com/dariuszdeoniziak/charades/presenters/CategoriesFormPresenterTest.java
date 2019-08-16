package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.data.datasources.CharadesDataSource;
import com.dariuszdeoniziak.charades.data.models.room.CategoryRoomModel;
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
    @Mock CharadesDataSource charadesDataSource;
    private CategoriesFormPresenter presenter;

    @Before
    public void setUp() {
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());

        MockitoAnnotations.initMocks(this);
        presenter = new CategoriesFormPresenter(charadesDataSource);
        presenter.onTakeView(view);
    }

    @After
    public void tearDown() {
        RxJavaPlugins.reset();
        reset(view, charadesDataSource);
    }

    @Test
    public void loadCategory() {
        // given
        Long categoryId = 1L;
        CategoryRoomModel category = new CategoryRoomModel();
        category.id = categoryId;
        when(charadesDataSource.getCategory(categoryId)).thenReturn(category);

        // when
        presenter.loadCategory(categoryId);

        // then
        verify(charadesDataSource).getCategory(categoryId);
        assertNotNull(presenter.category);
        assertEquals(categoryId, presenter.category.id);
        verify(view).showCategory(category);
    }

    @Test
    public void loadNullCategory() {
        // given
        Long categoryId = 1L;
        when(charadesDataSource.getCategory(categoryId)).thenReturn(null);

        // when
        presenter.loadCategory(categoryId);

        // then
        verify(charadesDataSource).getCategory(categoryId);
        assertNotNull(presenter.category);
        assertNotEquals(categoryId, presenter.category.id);
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
        assertEquals(categoryName, presenter.category.name);
        verify(charadesDataSource).saveCategory(presenter.category);
    }
}