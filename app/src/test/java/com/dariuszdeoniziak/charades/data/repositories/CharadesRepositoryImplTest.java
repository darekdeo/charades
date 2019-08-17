package com.dariuszdeoniziak.charades.data.repositories;

import com.dariuszdeoniziak.charades.data.datasources.CharadesDataSource;
import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.data.models.Charade;
import com.dariuszdeoniziak.charades.data.models.room.CategoryRoomModel;
import com.dariuszdeoniziak.charades.data.models.room.CharadeRoomModel;
import com.dariuszdeoniziak.charades.utils.Mapper;
import com.dariuszdeoniziak.charades.utils.RxJavaTestRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(RxJavaTestRunner.class)
public class CharadesRepositoryImplTest {

    @Mock
    Category category;
    @Mock
    CategoryRoomModel categoryRoomModel;
    @Mock
    Charade charade;
    @Mock
    CharadeRoomModel charadeRoomModel;
    @Mock
    CharadesDataSource charadesDataSource;
    @Mock
    Mapper<CategoryRoomModel, Category> fromCategoryRoomModelMapper;
    @Mock
    Mapper<Category, CategoryRoomModel> toCategoryRoomModelMapper;
    @Mock
    Mapper<CharadeRoomModel, Charade> fromCharadeRoomModelMapper;
    @Mock
    Mapper<Charade, CharadeRoomModel> toCharadeRoomModelMapper;
    private CharadesRepository charadesRepository;

    @Before
    public void setUp() {
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());

        MockitoAnnotations.initMocks(this);
        charadesRepository = new CharadesRepositoryImpl(
                charadesDataSource,
                toCategoryRoomModelMapper,
                fromCategoryRoomModelMapper,
                toCharadeRoomModelMapper,
                fromCharadeRoomModelMapper
        );
    }

    @After
    public void tearDown() {
        reset(
                charadesDataSource,
                toCategoryRoomModelMapper,
                fromCategoryRoomModelMapper,
                toCharadeRoomModelMapper,
                fromCharadeRoomModelMapper
        );
    }

    @Test
    public void saveCategory() {
        // given
        when(toCategoryRoomModelMapper.map(category)).thenReturn(categoryRoomModel);
        when(charadesDataSource.saveCategory(categoryRoomModel)).thenReturn(1L);

        // when
        TestObserver<Long> testObserver = charadesRepository.saveCategory(category).test();

        // then
        testObserver
                .assertNoErrors()
                .assertValue(1L);
        verify(toCategoryRoomModelMapper).map(category);
        verify(charadesDataSource).saveCategory(any());
    }

    @Test
    public void getCategory() {
        // given
        long categoryId = 1L;
        when(charadesDataSource.getCategory(categoryId)).thenReturn(categoryRoomModel);
        when(fromCategoryRoomModelMapper.map(categoryRoomModel)).thenReturn(category);

        // when
        TestObserver<Category> testObserver = charadesRepository.getCategory(categoryId).test();

        // then
        testObserver
                .assertNoErrors()
                .assertValue(category);
        verify(charadesDataSource).getCategory(categoryId);
        verify(fromCategoryRoomModelMapper).map(categoryRoomModel);
    }

    @Test
    public void getCategories() {
        // given
        List<CategoryRoomModel> list = Arrays.asList(categoryRoomModel, categoryRoomModel, categoryRoomModel);
        List<Category> categoryList = Arrays.asList(category, category, category);
        when(charadesDataSource.getCategories()).thenReturn(list);
        when(fromCategoryRoomModelMapper.map(categoryRoomModel)).thenReturn(category);

        // when
        TestObserver<List<Category>> testObserver = charadesRepository.getCategories().test();

        // then
        testObserver
                .assertNoErrors()
                .assertValue(categoryList);
        verify(charadesDataSource).getCategories();
        verify(fromCategoryRoomModelMapper, times(3)).map(categoryRoomModel);
    }

    @Test
    public void deleteCategory() {
        // given
        when(toCategoryRoomModelMapper.map(category)).thenReturn(categoryRoomModel);
        when(charadesDataSource.deleteCategory(categoryRoomModel)).thenReturn(1L);

        // when
        TestObserver<Long> testObserver = charadesRepository.deleteCategory(category).test();

        // then
        testObserver
                .assertNoErrors()
                .assertValue(1L);
        verify(toCategoryRoomModelMapper).map(category);
        verify(charadesDataSource).deleteCategory(categoryRoomModel);
    }

    @Test
    public void saveCharade() {
        // given
        when(toCharadeRoomModelMapper.map(charade)).thenReturn(charadeRoomModel);
        when(charadesDataSource.saveCharade(charadeRoomModel)).thenReturn(1L);

        // when
        TestObserver<Long> testObserver = charadesRepository.saveCharade(charade).test();

        // then
        testObserver
                .assertNoErrors()
                .assertValue(1L);
        verify(toCharadeRoomModelMapper).map(charade);
        verify(charadesDataSource).saveCharade(charadeRoomModel);
    }

    @Test
    public void getCharade() {
        // given
        long charadeId = 1L;
        when(charadesDataSource.getCharade(charadeId)).thenReturn(charadeRoomModel);
        when(fromCharadeRoomModelMapper.map(charadeRoomModel)).thenReturn(charade);

        // when
        TestObserver<Charade> testObserver = charadesRepository.getCharade(charadeId).test();

        // then
        testObserver
                .assertNoErrors()
                .assertValue(charade);
        verify(charadesDataSource).getCharade(charadeId);
        verify(fromCharadeRoomModelMapper).map(charadeRoomModel);
    }

    @Test
    public void getCharades() {
        // given
        List<CharadeRoomModel> list = Arrays.asList(charadeRoomModel, charadeRoomModel);
        List<Charade> charadeList = Arrays.asList(charade, charade);
        when(charadesDataSource.getCharades()).thenReturn(list);
        when(fromCharadeRoomModelMapper.map(charadeRoomModel)).thenReturn(charade);

        // when
        TestObserver<List<Charade>> testObserver = charadesRepository.getCharades().test();

        // then
        testObserver
                .assertNoErrors()
                .assertValue(charadeList);
        verify(charadesDataSource).getCharades();
        verify(fromCharadeRoomModelMapper, times(2)).map(charadeRoomModel);
    }

    @Test
    public void getCharadesFromCategory() {
        // given
        long categoryId = 1L;
        List<CharadeRoomModel> list = Arrays.asList(charadeRoomModel, charadeRoomModel);
        List<Charade> charadesList = Arrays.asList(charade, charade);
        when(charadesDataSource.getCharades(categoryId)).thenReturn(list);
        when(fromCharadeRoomModelMapper.map(charadeRoomModel)).thenReturn(charade);

        // when
        TestObserver<List<Charade>> testObserver = charadesRepository.getCharades(categoryId).test();

        // then
        testObserver
                .assertNoErrors()
                .assertValue(charadesList);
        verify(charadesDataSource).getCharades(categoryId);
        verify(fromCharadeRoomModelMapper, times(2)).map(charadeRoomModel);
    }

    @Test
    public void deleteCharade() {
        // given
        when(toCharadeRoomModelMapper.map(charade)).thenReturn(charadeRoomModel);
        when(charadesDataSource.deleteCharade(charadeRoomModel)).thenReturn(1L);

        // when
        TestObserver<Long> testObserver = charadesRepository.deleteCharade(charade).test();

        // then
        testObserver
                .assertNoErrors()
                .assertValue(1L);
        verify(toCharadeRoomModelMapper).map(charade);
        verify(charadesDataSource).deleteCharade(charadeRoomModel);
    }
}