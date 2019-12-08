package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.data.repositories.CharadesRepository;
import com.dariuszdeoniziak.charades.schedulers.TestSchedulerFactory;
import com.dariuszdeoniziak.charades.utils.RxJavaTestRunner;
import com.dariuszdeoniziak.charades.views.CategoriesFormContract;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(RxJavaTestRunner.class)
public class CategoriesFormPresenterTest {

    @Mock CategoriesFormContract.View view;
    @Mock CharadesRepository charadesRepository;
    private TestSchedulerFactory testSchedulerFactory = new TestSchedulerFactory();
    private CategoriesFormPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new CategoriesFormPresenter(charadesRepository, testSchedulerFactory);
        presenter.onTakeView(view);
    }

    @After
    public void tearDown() {
        testSchedulerFactory.reset();
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
        presenter.onLoadCategory(categoryId);

        // then
        verify(charadesRepository).getCategory(categoryId);
        assertNotNull(presenter.category);
        assertEquals(categoryId, presenter.category.id);
        verify(view).showCategory(category);
    }

    @Test
    public void doNotLoadNullCategoryId() {
        // when
        presenter.onLoadCategory(0L);

        // then
        verify(charadesRepository, never()).getCategory(anyLong());
        assertNotNull(presenter.category);
        verify(view, never()).showCategory(presenter.category);
    }

    @Test
    public void titleChangeShouldTriggerSaveCategoryTitleAfterTypingDelay() {
        // given
        Long categoryId = 1L;
        Category category = new Category();
        presenter.category = category;
        when(charadesRepository.saveCategory(category)).thenReturn(Single.just(categoryId));

        String testString = "test";
        int typingDelay = CategoriesFormPresenter.TYPING_DELAY;
        TestScheduler testScheduler = new TestScheduler();
        testSchedulerFactory.replaceComputationScheduler(testScheduler);
        presenter = new CategoriesFormPresenter(charadesRepository, testSchedulerFactory);

        // when
        presenter.onEditedCategoryTitle(testString);

        // then
        verify(charadesRepository, never()).saveCategory(any());
        testScheduler.advanceTimeBy(typingDelay, TimeUnit.SECONDS);
        verify(charadesRepository).saveCategory(presenter.category);
    }

    @Test
    public void titleChangeShouldTriggerSaveCategoryTitleAfterTypingDelayIfTitleHasNotChanged() {
        // given
        Long categoryId = 1L;
        String testString = "test";
        Category category = new Category();
        category.name = testString;
        when(charadesRepository.saveCategory(category)).thenReturn(Single.just(categoryId));

        int typingDelay = CategoriesFormPresenter.TYPING_DELAY;
        TestScheduler testScheduler = new TestScheduler();
        testSchedulerFactory.replaceComputationScheduler(testScheduler);
        presenter = new CategoriesFormPresenter(charadesRepository, testSchedulerFactory);
        presenter.category = category;

        // when
        presenter.onEditedCategoryTitle(testString);

        // then
        verify(charadesRepository, never()).saveCategory(any());
        testScheduler.advanceTimeBy(typingDelay, TimeUnit.SECONDS);
        verify(charadesRepository, never()).saveCategory(any());
    }

    @Test
    public void saveCategoryTitle() {
        // given
        Long categoryId = 1L;
        Category category = new Category();
        presenter.category = category;
        String categoryName = "test_category_title";
        when(charadesRepository.saveCategory(category)).thenReturn(Single.just(categoryId));

        // when
        presenter.onSaveCategoryTitle(categoryName);

        // then
        assertNotNull(presenter.category);
        assertEquals(categoryId, presenter.category.id);
        assertEquals(categoryName, presenter.category.name);
        verify(charadesRepository).saveCategory(presenter.category);
    }
}