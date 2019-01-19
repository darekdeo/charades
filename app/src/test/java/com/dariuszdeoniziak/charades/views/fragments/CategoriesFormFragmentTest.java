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
import org.robolectric.util.FragmentTestUtil;

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

    private CategoriesFormFragment fragment;
    private FragmentController<CategoriesFormFragment> controller;

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
    public void testInstantiation() {
        // given
        CategoriesFormFragment fragment = CategoriesFormFragment.newInstance();
        CategoriesFormFragment argFragment = CategoriesFormFragment.newInstance(1);

        // when
        FragmentTestUtil.startFragment(fragment); // impossible to test custom instantiation in new API

        // then
        assertEquals(0, fragment.categoryId);

        // also when
        FragmentTestUtil.startFragment(argFragment); // new API does not allow passing arguments

        // then
        assertEquals(1, argFragment.categoryId);
    }

    @Test
    public void onTakeView() {
        // given
        CategoriesFormFragment spy = spy(fragment);

        // when
        spy.onStart();

        // then
        verify(presenter).onTakeView(spy);
        verify(spy).setupViewActions();
        verify(presenter).loadCategory(spy.categoryId);
    }

    @Test
    public void onDropView() {
        // when
        fragment.onStop();

        // then
        verify(presenter).onDropView();
    }

    @Test
    public void onTitleEdited() {
        // given
        String testText = "test title";

        fragment.onStart();
        TestObserver<CharSequence> testObserver = fragment.titleTextChanges.test();

        // when
        fragment.editTextCategoryTitle.setText(testText);

        // then
        List<CharSequence> values = testObserver.values();
        CharSequence charSequence = values.get(values.size() - 1);
        assertEquals(testText, charSequence.toString());

        verify(presenter).saveCategoryTitle(testText);
    }

    @Test
    public void displayTextInfo() {
        // when
        fragment.showTextInfo("test");

        // then
        verify(androidWrapper).showToast(fragment.getActivity(), "test", Toast.LENGTH_SHORT);
    }
}
