package com.dariuszdeoniziak.charades.data.repositories;

import com.dariuszdeoniziak.charades.data.datasources.PreferencesDataSource;
import com.dariuszdeoniziak.charades.utils.RxJavaParameterizedTestRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;

import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;


@RunWith(RxJavaParameterizedTestRunner.class)
public class PreferencesRepositoryImplTest {

    @Parameterized.Parameter()
    public boolean isFirstRun;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> params = new ArrayList<>();
        params.add(new Object[]{true});
        params.add(new Object[]{false});
        return params;
    }

    @Mock
    PreferencesDataSource preferencesLocalDataSource;
    private PreferencesRepository preferencesRepository;

    @Before
    public void setUp() {
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());

        MockitoAnnotations.initMocks(this);
        preferencesRepository = new PreferencesRepositoryImpl(preferencesLocalDataSource);
    }

    @After
    public void tearDown() {
        Mockito.reset(preferencesLocalDataSource);
    }

    @Test
    public void saveFirstRun() {
        // when
        TestObserver testObserver = preferencesRepository.saveFirstRun().test();

        // then
        testObserver
                .assertNoErrors()
                .assertComplete();
        Mockito.verify(preferencesLocalDataSource).saveFirstRun();
    }

    @Test
    public void isFirstRun() {
        // given
        Mockito.when(preferencesLocalDataSource.isFirstRun()).thenReturn(isFirstRun);

        // when
        TestObserver<Boolean> testObserver = preferencesRepository.isFirstRun().test();

        // then
        testObserver
                .assertNoErrors()
                .assertValue(isFirstRun);
        Mockito.verify(preferencesLocalDataSource).isFirstRun();
    }

    @Test
    public void deleteFirstRun() {
        // when
        TestObserver testObserver = preferencesRepository.deleteFirstRun().test();

        // then
        testObserver
                .assertNoErrors()
                .assertComplete();
        Mockito.verify(preferencesLocalDataSource).deleteFirstRun();
    }
}