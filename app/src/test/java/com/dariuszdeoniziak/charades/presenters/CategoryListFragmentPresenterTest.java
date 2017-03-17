package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.views.CategoryListView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CategoryListFragmentPresenterTest {

    @Mock CategoryListView view;
    CategoryListFragmentPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new CategoryListFragmentPresenter(view);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void loadCategoriesShowsCategories() {

    }

}