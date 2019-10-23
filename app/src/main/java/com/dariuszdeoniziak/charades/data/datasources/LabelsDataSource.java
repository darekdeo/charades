package com.dariuszdeoniziak.charades.data.datasources;

import com.dariuszdeoniziak.charades.data.models.Label;

public interface LabelsDataSource {

    String getLabel(Label label);

    String getLabel(Label label, Object... formatArgs);
}
