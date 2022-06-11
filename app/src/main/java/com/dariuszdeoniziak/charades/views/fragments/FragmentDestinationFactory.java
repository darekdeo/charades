package com.dariuszdeoniziak.charades.views.fragments;

import com.dariuszdeoniziak.charades.navigators.Destination;
import com.dariuszdeoniziak.charades.navigators.DestinationFactory;

public class FragmentDestinationFactory implements DestinationFactory<BaseFragment> {
    public final static String CATEGORIES_LIST = "TAG_CATEGORIES_LIST";
    public final static String CATEGORIES_FORM = "TAG_CATEGORIES_FORM";

    @Override
    public BaseFragment create(Destination destination) {
        BaseFragment fragment;
        switch (destination.getTag()) {
            case CATEGORIES_LIST:
                fragment = new CategoriesListFragment();
                break;
            case CATEGORIES_FORM:
                fragment = new CategoriesFormFragment();
                break;
            default:
                fragment = null;
                break;
        }
        return fragment;
    }
}
