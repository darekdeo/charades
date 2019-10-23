package com.dariuszdeoniziak.charades.data.repositories;

import com.dariuszdeoniziak.charades.data.datasources.LabelsDataSource;
import com.dariuszdeoniziak.charades.data.models.Label;

public class LabelsRepositoryImpl implements LabelsRepository {

    private final LabelsDataSource resourcesLabelsDataSource;

    public LabelsRepositoryImpl(LabelsDataSource resourcesLabelsDataSource) {
        this.resourcesLabelsDataSource = resourcesLabelsDataSource;
    }

    @Override
    public String getLabel(Label label) {
        return resourcesLabelsDataSource.getLabel(label);
    }

    @Override
    public String getLabel(Label label, Object... formatArgs) {
        return resourcesLabelsDataSource.getLabel(label, formatArgs);
    }
}
