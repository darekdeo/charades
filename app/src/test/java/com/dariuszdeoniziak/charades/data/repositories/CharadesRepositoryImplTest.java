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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;


@RunWith(RxJavaTestRunner.class)
public class CharadesRepositoryImplTest {

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
        Mockito.reset(
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
        Category category = new Category();
        CategoryRoomModel categoryRoomModel = new CategoryRoomModel();
        Mockito.when(toCategoryRoomModelMapper.map(category)).thenReturn(categoryRoomModel);
        Mockito.when(charadesDataSource.saveCategory(categoryRoomModel)).thenReturn(1L);

        // when
        TestObserver<Long> testObserver = charadesRepository.saveCategory(category).test();

        // then
        testObserver
                .assertNoErrors()
                .assertValue(1L);
        Mockito.verify(toCategoryRoomModelMapper).map(category);
        Mockito.verify(charadesDataSource).saveCategory(Mockito.any());
    }

    @Test
    public void getCategory() {
        // given
        long categoryId = 1L;
        CategoryRoomModel categoryRoomModel = new CategoryRoomModel();
        Category category = new Category();
        Mockito.when(charadesDataSource.getCategory(categoryId)).thenReturn(categoryRoomModel);
        Mockito.when(fromCategoryRoomModelMapper.map(categoryRoomModel)).thenReturn(category);

        // when
        TestObserver<Category> testObserver = charadesRepository.getCategory(categoryId).test();

        // then
        testObserver
                .assertNoErrors()
                .assertValue(category);
        Mockito.verify(charadesDataSource).getCategory(categoryId);
        Mockito.verify(fromCategoryRoomModelMapper).map(categoryRoomModel);
    }

    @Test
    public void getCategories() {
        // given
        CategoryRoomModel categoryRoomModel = new CategoryRoomModel();
        List<CategoryRoomModel> list = Arrays.asList(categoryRoomModel, categoryRoomModel, categoryRoomModel);
        Category category = new Category();
        List<Category> categoryList = Arrays.asList(category, category, category);
        Mockito.when(charadesDataSource.getCategories()).thenReturn(list);
        Mockito.when(fromCategoryRoomModelMapper.map(categoryRoomModel)).thenReturn(category);

        // when
        TestObserver<List<Category>> testObserver = charadesRepository.getCategories().test();

        // then
        testObserver
                .assertNoErrors()
                .assertValue(categoryList);
        Mockito.verify(charadesDataSource).getCategories();
        Mockito.verify(fromCategoryRoomModelMapper, Mockito.times(3)).map(categoryRoomModel);
    }

    @Test
    public void deleteCategory() {
        // given
        Category category = new Category();
        CategoryRoomModel categoryRoomModel = new CategoryRoomModel();
        Mockito.when(toCategoryRoomModelMapper.map(category)).thenReturn(categoryRoomModel);
        Mockito.when(charadesDataSource.deleteCategory(categoryRoomModel)).thenReturn(1L);

        // when
        TestObserver<Long> testObserver = charadesRepository.deleteCategory(category).test();

        // then
        testObserver
                .assertNoErrors()
                .assertValue(1L);
        Mockito.verify(toCategoryRoomModelMapper).map(category);
        Mockito.verify(charadesDataSource).deleteCategory(categoryRoomModel);
    }

    @Test
    public void saveCharade() {
        // given
        Charade charade = new Charade();
        CharadeRoomModel charadeRoomModel = new CharadeRoomModel();
        Mockito.when(toCharadeRoomModelMapper.map(charade)).thenReturn(charadeRoomModel);
        Mockito.when(charadesDataSource.saveCharade(charadeRoomModel)).thenReturn(1L);

        // when
        TestObserver<Long> testObserver = charadesRepository.saveCharade(charade).test();

        // then
        testObserver
                .assertNoErrors()
                .assertValue(1L);
        Mockito.verify(toCharadeRoomModelMapper).map(charade);
        Mockito.verify(charadesDataSource).saveCharade(charadeRoomModel);
    }

    @Test
    public void getCharade() {
        // given
        long charadeId = 1L;
        Charade charade = new Charade();
        CharadeRoomModel charadeRoomModel = new CharadeRoomModel();
        Mockito.when(charadesDataSource.getCharade(charadeId)).thenReturn(charadeRoomModel);
        Mockito.when(fromCharadeRoomModelMapper.map(charadeRoomModel)).thenReturn(charade);

        // when
        TestObserver<Charade> testObserver = charadesRepository.getCharade(charadeId).test();

        // then
        testObserver
                .assertNoErrors()
                .assertValue(charade);
        Mockito.verify(charadesDataSource).getCharade(charadeId);
        Mockito.verify(fromCharadeRoomModelMapper).map(charadeRoomModel);
    }

    @Test
    public void getCharades() {
        // given
        CharadeRoomModel charadeRoomModel = new CharadeRoomModel();
        List<CharadeRoomModel> list = Arrays.asList(charadeRoomModel, charadeRoomModel);
        Charade charade = new Charade();
        List<Charade> charadeList = Arrays.asList(charade, charade);
        Mockito.when(charadesDataSource.getCharades()).thenReturn(list);
        Mockito.when(fromCharadeRoomModelMapper.map(charadeRoomModel)).thenReturn(charade);

        // when
        TestObserver<List<Charade>> testObserver = charadesRepository.getCharades().test();

        // then
        testObserver
                .assertNoErrors()
                .assertValue(charadeList);
        Mockito.verify(charadesDataSource).getCharades();
        Mockito.verify(fromCharadeRoomModelMapper, Mockito.times(2)).map(charadeRoomModel);
    }

    @Test
    public void getCharadesFromCategory() {
        // given
        long categoryId = 1L;
        CharadeRoomModel charadeRoomModel = new CharadeRoomModel();
        List<CharadeRoomModel> list = Arrays.asList(charadeRoomModel, charadeRoomModel);
        Charade charade = new Charade();
        List<Charade> charadesList = Arrays.asList(charade, charade);
        Mockito.when(charadesDataSource.getCharades(categoryId)).thenReturn(list);
        Mockito.when(fromCharadeRoomModelMapper.map(charadeRoomModel)).thenReturn(charade);

        // when
        TestObserver<List<Charade>> testObserver = charadesRepository.getCharades(categoryId).test();

        // then
        testObserver
                .assertNoErrors()
                .assertValue(charadesList);
        Mockito.verify(charadesDataSource).getCharades(categoryId);
        Mockito.verify(fromCharadeRoomModelMapper, Mockito.times(2)).map(charadeRoomModel);
    }

    @Test
    public void deleteCharade() {
        // given
        Charade charade = new Charade();
        CharadeRoomModel charadeRoomModel = new CharadeRoomModel();
        Mockito.when(toCharadeRoomModelMapper.map(charade)).thenReturn(charadeRoomModel);
        Mockito.when(charadesDataSource.deleteCharade(charadeRoomModel)).thenReturn(1L);

        // when
        TestObserver<Long> testObserver = charadesRepository.deleteCharade(charade).test();

        // then
        testObserver
                .assertNoErrors()
                .assertValue(1L);
        Mockito.verify(toCharadeRoomModelMapper).map(charade);
        Mockito.verify(charadesDataSource).deleteCharade(charadeRoomModel);
    }
}