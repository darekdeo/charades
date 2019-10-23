package com.dariuszdeoniziak.charades.data.repositories;

import com.dariuszdeoniziak.charades.data.datasources.LabelsDataSource;
import com.dariuszdeoniziak.charades.data.models.Label;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LabelsRepositoryImplTest {

    @Mock
    LabelsDataSource labelsDataSource;
    private LabelsRepository labelsRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        labelsRepository = new LabelsRepositoryImpl(labelsDataSource);
    }

    @After
    public void tearDown() {
        reset(labelsDataSource);
    }

    @Test
    public void getLabelShouldCallDataSource() {
        // given
        Label testLabel = any();
        when(labelsDataSource.getLabel(testLabel)).thenReturn(anyString());

        // when
        labelsRepository.getLabel(testLabel);

        // then
        verify(labelsDataSource).getLabel(testLabel);
    }

    @Test
    public void getLabelWithArgsShouldCallDataSource() {
        // given
        Label testLabel = Label.categories_list_dialog_confirm_delete_message;
        String testArg = "testArg";
        when(labelsDataSource.getLabel(testLabel, testArg)).thenReturn("testResult");

        // when
        labelsRepository.getLabel(testLabel, testArg);

        // then
        verify(labelsDataSource).getLabel(testLabel, testArg);
    }
}