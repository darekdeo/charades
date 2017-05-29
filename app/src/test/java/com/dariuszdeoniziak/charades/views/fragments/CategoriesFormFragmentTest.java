package com.dariuszdeoniziak.charades.views.fragments;

import com.dariuszdeoniziak.charades.App;
import com.dariuszdeoniziak.charades.BuildConfig;
import com.dariuszdeoniziak.charades.presenters.CategoriesFormPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.FragmentController;
import org.robolectric.annotation.Config;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18, application = App.class)
public class CategoriesFormFragmentTest {

    CategoriesFormFragment fragment;
    FragmentController<CategoriesFormFragment> controller;

    @Mock CategoriesFormPresenter presenter;

    @Before
    public void setUp() {
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
        RxJavaPlugins.setScheduleHandler(new Function<Runnable, Runnable>() {
            @Override
            public Runnable apply(Runnable runnable) throws Exception {
                return new Runnable() {
                    @Override
                    public void run() {
                        System.out.print("haha");
                    }
                };
            }
        });

        MockitoAnnotations.initMocks(this);
        fragment = Robolectric.buildFragment(CategoriesFormFragment.class).create().get();
        assertNotNull(fragment);
        fragment.replace(presenter); // replace injected presenter with mock

        controller = Robolectric.buildFragment(CategoriesFormFragment.class);
    }

    @After
    public void tearDown() {
        reset(presenter);
    }

    @Test
    public void onTakeView() throws Exception {
        CategoriesFormFragment spy = spy(fragment);
        spy.onResume();
        verify(presenter).onTakeView(spy);
        verify(spy).setupViewActions();
    }

    @Test
    public void onDropView() throws Exception {
        fragment.onDestroyView();
        verify(presenter).onDropView();
    }

    @Test
    public void onTitleEdited() throws Exception {
        String testText = "test title";

        TestScheduler testScheduler = new TestScheduler();
        fragment.onResume();
        TestObserver<CharSequence> testObserver = fragment.titleTextChanges.test();
        fragment.titleTextChanges.debounce(1, TimeUnit.SECONDS, testScheduler);

        fragment.editTextCategoryTitle.setText(testText);
        testScheduler.triggerActions();
        testScheduler.advanceTimeTo(1, TimeUnit.SECONDS);

        List<CharSequence> values = testObserver.values();
        CharSequence charSequence = values.get(values.size() - 1);
        assertEquals(testText, charSequence.toString());

        verify(presenter).onTitleEdited(testText);
    }
}
