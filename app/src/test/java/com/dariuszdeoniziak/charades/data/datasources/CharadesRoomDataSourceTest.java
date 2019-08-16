package com.dariuszdeoniziak.charades.data.datasources;

import android.content.Context;

import com.dariuszdeoniziak.charades.data.datasources.room.CharadesRoomDataSource;
import com.dariuszdeoniziak.charades.data.datasources.room.CharadesRoomDatabase;
import com.dariuszdeoniziak.charades.data.models.room.CategoryRoomModel;
import com.dariuszdeoniziak.charades.data.models.room.CharadeRoomModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class CharadesRoomDataSourceTest {

    private CharadesDataSource interactor;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        CharadesRoomDatabase database = Room
                .inMemoryDatabaseBuilder(
                        context,
                        CharadesRoomDatabase.class
                )
                .allowMainThreadQueries()
                .build();
        interactor = new CharadesRoomDataSource(database);
    }

    @After
    public void tearDown() {
        interactor.finish();
    }

    @Test
    public void crudCategory() {
        CategoryRoomModel category = new CategoryRoomModel();
        category.name = "test_category_name";

        // create
        assertEquals("test_category_name", category.name);
        long id = interactor.saveCategory(category);
        assertTrue(id > 0);

        // read
        List<CategoryRoomModel> categories = interactor.getCategories();
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
        CharadeRoomModel charade = new CharadeRoomModel();
        charade.name = "test_charade_name";

        // create
        assertEquals("test_charade_name", charade.name);
        long id = interactor.saveCharade(charade);
        assertTrue(id > 0);

        // read
        List<CharadeRoomModel> charades = interactor.getCharades();
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
        CategoryRoomModel category = new CategoryRoomModel();
        category.name = "test_category_name";
        category.id = interactor.saveCategory(category);

        CharadeRoomModel charade = new CharadeRoomModel();
        charade.name = "test_charade_name";
        charade.categoryId = category.id;
        charade.id = interactor.saveCharade(charade);

        // when
        List<CharadeRoomModel> charades = interactor.getCharades(category.id);

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