package com.dariuszdeoniziak.charades.data.datasources.resourcelabels;

import android.content.Context;

import com.dariuszdeoniziak.charades.data.datasources.LabelsDataSource;
import com.dariuszdeoniziak.charades.data.models.Label;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class ResourcesLabelsDataSourceTest {

    private LabelsDataSource labelsDataSource;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        labelsDataSource = new ResourcesLabelsDataSource(context);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getLabel() {
        // when then
        Arrays.asList(Label.values()).forEach(label ->
                Assert.assertFalse(labelsDataSource.getLabel(label).isEmpty())
        );
    }

    @Test
    public void getLabelWithArgs() {
        // when then
        Assert.assertFalse(labelsDataSource.getLabel(Label.categories_list_dialog_confirm_delete_message, "category").isEmpty());

        // and when then
        Assert.assertTrue(labelsDataSource.getLabel(Label.categories_list_dialog_confirm_delete_message, "category").contains("category"));
    }
}