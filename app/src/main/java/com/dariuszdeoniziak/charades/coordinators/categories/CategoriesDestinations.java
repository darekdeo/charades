package com.dariuszdeoniziak.charades.coordinators.categories;

import com.dariuszdeoniziak.charades.navigators.Destination;

public enum CategoriesDestinations implements Destination {

    LIST("CATEGORIES_LIST"),
    FORM("CATEGORIES_FORM");

    final String tag;

    CategoriesDestinations(String tag) {
        this.tag = tag;
    }

    @Override
    public String getTag() {
        return tag;
    }

}
