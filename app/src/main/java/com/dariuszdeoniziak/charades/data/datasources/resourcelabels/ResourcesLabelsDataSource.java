package com.dariuszdeoniziak.charades.data.datasources.resourcelabels;

import android.content.Context;
import android.content.res.Resources;

import com.dariuszdeoniziak.charades.data.datasources.LabelsDataSource;
import com.dariuszdeoniziak.charades.data.models.Label;

import javax.inject.Inject;

public class ResourcesLabelsDataSource implements LabelsDataSource {

    private final Resources resources;
    private final String packageName;

    @Inject
    public ResourcesLabelsDataSource(Context context) {
        resources = context.getResources();
        packageName = context.getPackageName();
    }

    @Override
    public String getLabel(Label label) {
        int identifier = getIdentifier(label);
        return resources.getString(identifier);
    }

    @Override
    public String getLabel(Label label, Object... formatArgs) {
        int identifier = getIdentifier(label);
        return resources.getString(identifier, formatArgs);
    }

    private int getIdentifier(Label label) {
        return resources.getIdentifier(label.name(), "string", packageName);
    }
}
