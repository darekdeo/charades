package com.dariuszdeoniziak.charades.models;

import android.content.Context;

import com.dariuszdeoniziak.charades.App;
import com.dariuszdeoniziak.charades.BuildConfig;
import com.orm.SugarContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18, assetDir = "assets", application = App.class)
public class CategoryTest {

    @Before
    public void setUp() throws Exception {
        Context context = RuntimeEnvironment.application;
        SugarContext.init(context);
    }

    @After
    public void tearDown() throws Exception {
        SugarContext.terminate();
    }

    @Test
    public void createReadUpdateDeleteCategory() throws Exception {
        Category category = Category.builder()
                .name("test_category_name")
                .build();
        assertTrue(category.name.equals("test_category_name"));
        category.save();

        category = Category.findById(Category.class, category.getId());
        assertTrue(category.name.equals("test_category_name"));

        category.name = "new_test_category_name";
        category.save();

        category = Category.findById(Category.class, category.getId());
        assertTrue(category.name.equals("new_test_category_name"));

        category.delete();
        category = Category.findById(Category.class, category.getId());
        assertNull(category);
    }

    @Test
    public void getCharades() throws Exception {

    }

}