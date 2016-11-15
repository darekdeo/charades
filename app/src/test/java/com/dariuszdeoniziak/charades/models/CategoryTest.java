package com.dariuszdeoniziak.charades.models;

import android.content.Context;

import com.dariuszdeoniziak.charades.App;
import com.dariuszdeoniziak.charades.BuildConfig;
import com.orm.SugarContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

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
        Category category = Category.builder()
                .name("test_category_name")
                .build();
        category.save();

        Charade charade = Charade.builder()
                .name("test_charade_name")
                .category(category.getId())
                .build();
        charade.save();

        List<Charade> charades = category.getCharades();
        assertNotNull(charades);
        assertFalse(charades.isEmpty());
        assertEquals(charade.name, charades.get(0).name);

        category.delete();
        category = Category.findById(Category.class, category.getId());
        assertNull(category);

        charade.delete(); // is not be deleted recursively through deleting category
        charade = Charade.findById(Charade.class, charade.getId());
        assertNull(charade);
    }

}