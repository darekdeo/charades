package com.dariuszdeoniziak.charades.models.interactors;

import android.content.Context;

import com.dariuszdeoniziak.charades.App;
import com.dariuszdeoniziak.charades.BuildConfig;
import com.dariuszdeoniziak.charades.models.Category;
import com.dariuszdeoniziak.charades.models.Charade;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18, application = App.class)
public class SugarOrmInteractorTest {

    ModelInteractor interactor;

    @Before
    public void setUp() throws Exception {
        Context context = RuntimeEnvironment.application;
        interactor = new SugarOrmInteractor(context);
    }

    @After
    public void tearDown() throws Exception {
        interactor.finish();
    }

    @Test
    public void crudCategory() throws Exception {
        Category category = Category.builder()
                .name("test_category_name")
                .build();

        // create
        assertTrue(category.getName().equals("test_category_name"));
        long id = interactor.saveCategory(category);
        assertTrue(id > 0);

        // read
        List<Category> categories = interactor.getCategories();
        assertNotNull(categories);
        assertFalse(categories.isEmpty());

        category = interactor.getCategory(id);
        assertTrue(category.getName().equals("test_category_name"));

        // update
        category.setName("new_test_category_name");
        interactor.saveCategory(category);

        category = interactor.getCategory(id);
        assertTrue(category.getName().equals("new_test_category_name"));

        // delete
        boolean deleted = interactor.deleteCategory(category);
        assertTrue(deleted);
        category = interactor.getCategory(id);
        assertNull(category);
    }

    @Test
    public void crudCharade() throws Exception {
        Charade charade = Charade.builder()
                .name("test_charade_name")
                .build();

        // create
        assertTrue(charade.getName().equals("test_charade_name"));
        long id = interactor.saveCharade(charade);
        assertTrue(id > 0);

        // read
        List<Charade> charades = interactor.getCharades();
        assertNotNull(charades);
        assertFalse(charades.isEmpty());

        charade = interactor.getCharade(id);
        assertTrue(charade.getName().equals("test_charade_name"));

        // update
        charade.setName("new_test_charade_name");
        interactor.saveCharade(charade);

        charade = interactor.getCharade(id);
        assertTrue(charade.getName().equals("new_test_charade_name"));

        // delete
        boolean deleted = interactor.deleteCharade(charade);
        assertTrue(deleted);
        charade = interactor.getCharade(id);
        assertNull(charade);
    }

    @Test
    public void getCharadesFromCategory() throws Exception {
        Category category = Category.builder()
                .name("test_category_name")
                .build();
        interactor.saveCategory(category);

        Charade charade = Charade.builder()
                .name("test_charade_name")
                .category(category.getId())
                .build();
        interactor.saveCharade(charade);

        List<Charade> charades = interactor.getCharades(category);
        assertNotNull(charades);
        assertFalse(charades.isEmpty());
        assertEquals(charade.getName(), charades.get(0).getName());

        boolean deleted = interactor.deleteCategory(category);
        assertTrue(deleted);
        category = interactor.getCategory(category.getId());
        assertNull(category);

        deleted = interactor.deleteCharade(charade); // because is not deleted recursively through deleting category
        assertTrue(deleted);
        charade = interactor.getCharade(charade.getId());
        assertNull(charade);
    }

}