package com.dariuszdeoniziak.charades.views.models.mappers;

import com.dariuszdeoniziak.charades.utils.Mapper;
import com.dariuszdeoniziak.charades.views.CategoryScreen;
import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;
import com.dariuszdeoniziak.charades.views.fragments.CategoriesFormFragment;
import com.dariuszdeoniziak.charades.views.fragments.CategoriesListFragment;


public class ToCategoryScreenMapper implements Mapper<BaseFragment, CategoryScreen> {

    @Override
    public CategoryScreen map(BaseFragment fragment) {
        if (fragment instanceof CategoriesFormFragment)
            return CategoryScreen.FORM;
        if (fragment instanceof CategoriesListFragment)
            return CategoryScreen.LIST;
        else
            return CategoryScreen.NONE;
    }
}
