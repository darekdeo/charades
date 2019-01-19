package com.dariuszdeoniziak.charades.models.interactors;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.dariuszdeoniziak.charades.App;
import com.dariuszdeoniziak.charades.BuildConfig;
import com.dariuszdeoniziak.charades.models.Category;
import com.dariuszdeoniziak.charades.models.Charade;
import com.dariuszdeoniziak.charades.models.CharadesRoomDatabase;

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
public class RoomModelInteractorTest {

    private ModelInteractor interactor;

    @Before
    public void setUp() {
        Context context = RuntimeEnvironment.application;
        CharadesRoomDatabase database = Room
                .inMemoryDatabaseBuilder(
                        context,
                        CharadesRoomDatabase.class
                )
                .allowMainThreadQueries()
                .build();
        interactor = new RoomModelInteractor(database);
    }

    @After
    public void tearDown() {
        interactor.finish();
    }

    @Test
    public void crudCategory() {
        Category category = new Category();
        category.name = "test_category_name";

        // create
        assertEquals("test_category_name", category.name);
        long id = interactor.saveCategory(category);
        assertTrue(id > 0);

        // read
        List<Category> categories = interactor.getCategories();
        assertNotNull(categories);
        assertFalse(categories.isEmpty());

        category = interactor.getCategory(id);
        assertEquals("test_category_name", category.name);

        // update
        category.name = "new_test_category_name";
        interactor.saveCategory(category);

        category = interactor.getCategory(id);
        assertEquals("new_test_category_name", category.name);

        // delete
        long deleted = interactor.deleteCategory(category);
        assertEquals(1, deleted);
        category = interactor.getCategory(id);
        assertNull(category);
    }

    @Test
    public void crudCharade() {
        // given
        Charade charade = new Charade();
        charade.name = "test_charade_name";

        // create
        assertEquals("test_charade_name", charade.name);
        long id = interactor.saveCharade(charade);
        assertTrue(id > 0);

        // read
        List<Charade> charades = interactor.getCharades();
        assertNotNull(charades);
        assertFalse(charades.isEmpty());

        charade = interactor.getCharade(id);
        assertEquals("test_charade_name", charade.name);

        // update
        charade.name = "new_test_charade_name";
        interactor.saveCharade(charade);

        charade = interactor.getCharade(id);
        assertEquals("new_test_charade_name", charade.name);

        // delete
        long deleted = interactor.deleteCharade(charade);
        assertEquals(1, deleted);
        charade = interactor.getCharade(id);
        assertNull(charade);
    }

    @Test
    public void getCharadesFromCategory() {
        // given
        Category category = new Category();
        category.name = "test_category_name";
        category.id = interactor.saveCategory(category);

        Charade charade = new Charade();
        charade.name = "test_charade_name";
        charade.categoryId = category.id;
        charade.id = interactor.saveCharade(charade);

        // when
        List<Charade> charades = interactor.getCharades(category.id);

        // then
        assertNotNull(charades);
        assertFalse(charades.isEmpty());
        assertEquals(charade.name, charades.get(0).name);

        // also when
        long deleted = interactor.deleteCategory(category);
        category = interactor.getCategory(category.id);
        charade = interactor.getCharade(charade.id);

        // then
        assertEquals(1, deleted);
        assertNull(category);
        assertNull(charade);
    }

}