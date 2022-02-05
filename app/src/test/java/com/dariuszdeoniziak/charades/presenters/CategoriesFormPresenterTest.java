package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.data.models.Charade;
import com.dariuszdeoniziak.charades.data.repositories.CharadesRepository;
import com.dariuszdeoniziak.charades.data.repositories.LabelsRepository;
import com.dariuszdeoniziak.charades.schedulers.TestSchedulerFactory;
import com.dariuszdeoniziak.charades.utils.Mapper;
import com.dariuszdeoniziak.charades.utils.RxJavaTestRunner;
import com.dariuszdeoniziak.charades.views.CategoriesFormContract;
import com.dariuszdeoniziak.charades.views.models.CharadeListItemModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.TestScheduler;

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
    @Mock LabelsRepository labelsRepository;
    @Mock CharadesRepository charadesRepository;
    @Mock Mapper<Charade, CharadeListItemModel> toCharadeListItemModelMapper;
    private TestSchedulerFactory testSchedulerFactory = new TestSchedulerFactory();
    private CategoriesFormPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        testSchedulerFactory.reset();
        reset(view, charadesRepository);
    }

    @Test
    public void loadCategory() {
        // given
        presenter = new CategoriesFormPresenter(labelsRepository, charadesRepository, testSchedulerFactory, toCharadeListItemModelMapper);
        presenter.onTakeView(view);
        Long categoryId = 1L;
        Category category = new Category();
        category.id = categoryId;
        Charade charade = new Charade();
        charade.categoryId = categoryId;
        CharadeListItemModel charadeListItemModel = new CharadeListItemModel();
        List<Charade> charadeList = Collections.singletonList(charade);
        List<CharadeListItemModel> charadeListItemModelList = Collections.singletonList(charadeListItemModel);
        when(charadesRepository.getCategory(categoryId)).thenReturn(Single.just(category));
        when(charadesRepository.getCharades(categoryId)).thenReturn(Single.just(charadeList));
        when(toCharadeListItemModelMapper.map(charade)).thenReturn(charadeListItemModel);

        // when
        presenter.onLoadCategory(categoryId);

        // then
        verify(charadesRepository).getCategory(categoryId);
        verify(charadesRepository).getCharades(categoryId);
        assertNotNull(presenter.category);
        assertEquals(categoryId, presenter.category.id);
        verify(view).showCategory(category);
        verify(view).showCharades(charadeListItemModelList);
    }

    @Test
    public void doNotLoadNullCategoryId() {
        // given
        presenter = new CategoriesFormPresenter(labelsRepository, charadesRepository, testSchedulerFactory, toCharadeListItemModelMapper);
        presenter.onTakeView(view);

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
        String testString = "test";
        int typingDelay = CategoriesFormPresenter.TYPING_DELAY;
        TestScheduler testScheduler = new TestScheduler();
        testSchedulerFactory.replaceComputationScheduler(testScheduler);
        Long categoryId = 1L;
        Category category = new Category();
        presenter = new CategoriesFormPresenter(labelsRepository, charadesRepository, testSchedulerFactory, toCharadeListItemModelMapper);
        presenter.onTakeView(view);
        presenter.category = category;
        when(charadesRepository.saveCategory(category)).thenReturn(Single.just(categoryId));

        // when
        presenter.title.set(testString);

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
        presenter = new CategoriesFormPresenter(labelsRepository, charadesRepository, testSchedulerFactory, toCharadeListItemModelMapper);
        presenter.category = category;

        // when
        presenter.title.set(testString);

        // then
        verify(charadesRepository, never()).saveCategory(any());
        testScheduler.advanceTimeBy(typingDelay, TimeUnit.SECONDS);
        verify(charadesRepository, never()).saveCategory(any());
    }

    @Test
    public void saveCategoryTitle() {
        // given
        presenter = new CategoriesFormPresenter(labelsRepository, charadesRepository, testSchedulerFactory, toCharadeListItemModelMapper);
        presenter.onTakeView(view);
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