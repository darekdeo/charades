package com.dariuszdeoniziak.charades.data.repositories;

import com.dariuszdeoniziak.charades.data.models.Label;

public interface LabelsRepository {

    String getLabel(Label label);

    String getLabel(Label label, Object... formatArgs);
}
