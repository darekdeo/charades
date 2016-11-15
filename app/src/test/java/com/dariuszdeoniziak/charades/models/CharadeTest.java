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
public class CharadeTest {

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
    public void createReadUpdateDeleteCharade() {
        Charade charade = Charade.builder()
                .name("test_charade_name")
                .build();

        assertTrue(charade.name.equals("test_charade_name"));
        charade.save();

        charade = Charade.findById(Charade.class, charade.getId());
        assertTrue(charade.name.equals("test_charade_name"));

        charade.name = "new_test_charade_name";
        charade.save();

        charade = Charade.findById(Charade.class, charade.getId());
        assertTrue(charade.name.equals("new_test_charade_name"));

        charade.delete();
        charade = Charade.findById(Charade.class, charade.getId());
        assertNull(charade);
    }
}