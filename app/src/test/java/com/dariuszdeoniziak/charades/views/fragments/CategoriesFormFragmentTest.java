package com.dariuszdeoniziak.charades.views.fragments;

import android.widget.Toast;

import com.dariuszdeoniziak.charades.App;
import com.dariuszdeoniziak.charades.BuildConfig;
import com.dariuszdeoniziak.charades.presenters.CategoriesFormPresenter;
import com.dariuszdeoniziak.charades.utils.AndroidStaticsWrapper;

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

import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

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
    @Mock AndroidStaticsWrapper androidWrapper;

    @Before
    public void setUp() {
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setComputationSchedulerHandler(scheduler -> Schedulers.trampoline());

        MockitoAnnotations.initMocks(this);
        fragment = Robolectric.buildFragment(CategoriesFormFragment.class).create().get();
        assertNotNull(fragment);
        fragment.replace(presenter, androidWrapper); // replace injected presenter with mock

        controller = Robolectric.buildFragment(CategoriesFormFragment.class);
    }

    @After
    public void tearDown() {
        reset(presenter);
    }

    @Test
    public void onTakeView() throws Exception {
        CategoriesFormFragment spy = spy(fragment);
        spy.onStart();
        verify(presenter).onTakeView(spy);
        verify(spy).setupViewActions();
    }

    @Test
    public void onDropView() throws Exception {
        fragment.onStop();
        verify(presenter).onDropView();
    }

    @Test
    public void onTitleEdited() throws Exception {
        String testText = "test title";

        fragment.onStart();
        TestObserver<CharSequence> testObserver = fragment.titleTextChanges.test();

        fragment.editTextCategoryTitle.setText(testText);

        List<CharSequence> values = testObserver.values();
        CharSequence charSequence = values.get(values.size() - 1);
        assertEquals(testText, charSequence.toString());

        verify(presenter).onTitleEdited(testText);
    }

    @Test
    public void testDisplayTextInfo() throws Exception {
        fragment.displayTextInfo("test");
        verify(androidWrapper).showToast(fragment.getActivity(), "test", Toast.LENGTH_SHORT);
    }
}
