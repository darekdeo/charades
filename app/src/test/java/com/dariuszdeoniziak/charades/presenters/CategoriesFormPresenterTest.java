package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.data.repositories.CharadesRepository;
import com.dariuszdeoniziak.charades.utils.RxJavaTestRunner;
import com.dariuszdeoniziak.charades.views.CategoriesFormView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Single;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RxJavaTestRunner.class)
public class CategoriesFormPresenterTest {

    @Mock CategoriesFormView view;
    @Mock CharadesRepository charadesRepository;
    private CategoriesFormPresenter presenter;

    @Before
    public void setUp() {
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());

        MockitoAnnotations.initMocks(this);
        presenter = new CategoriesFormPresenter(charadesRepository);
        presenter.onTakeView(view);
    }

    @After
    public void tearDown() {
        RxJavaPlugins.reset();
        reset(view, charadesRepository);
    }

    @Test
    public void loadCategory() {
        // given
        Long categoryId = 1L;
        Category category = new Category();
        category.id = categoryId;
        when(charadesRepository.getCategory(categoryId)).thenReturn(Single.just(category));

        // when
        presenter.loadCategory(categoryId);

        // then
        verify(charadesRepository).getCategory(categoryId);
        assertNotNull(presenter.category);
        assertEquals(categoryId, presenter.category.id);
        verify(view).showCategory(category);
    }

    @Test
    public void doNotLoadNullCategoryId() {
        // when
        presenter.loadCategory(0L);

        // then
        verify(charadesRepository, Mockito.never()).getCategory(Mockito.anyLong());
        assertNotNull(presenter.category);
        verify(view, Mockito.never()).showCategory(presenter.category);
    }

    @Test
    public void saveCategoryTitle() {
        // given
        Category category = new Category();
        presenter.category = category;
        String categoryName = "test_category_title";
        Mockito.when(charadesRepository.saveCategory(category)).thenReturn(Single.just(1L));

        // when
        presenter.saveCategoryTitle(categoryName);

        // then
        assertNotNull(presenter.category);
        assertEquals(categoryName, presenter.category.name);
        verify(charadesRepository).saveCategory(presenter.category);
    }
}