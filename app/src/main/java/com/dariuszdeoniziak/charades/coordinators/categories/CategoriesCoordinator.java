package com.dariuszdeoniziak.charades.coordinators.categories;

import com.dariuszdeoniziak.charades.coordinators.Coordinator;
import com.dariuszdeoniziak.charades.views.CategoriesFormContract;
import com.dariuszdeoniziak.charades.views.CategoriesListContract;

public interface CategoriesCoordinator extends
        Coordinator<CategoriesResult>,
        CategoriesListContract.Coordination,
        CategoriesFormContract.Coordination {}
