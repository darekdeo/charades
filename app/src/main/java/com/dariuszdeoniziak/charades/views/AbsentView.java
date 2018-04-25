package com.dariuszdeoniziak.charades.views;


import com.dariuszdeoniziak.charades.models.Category;
import com.dariuszdeoniziak.charades.utils.Logger;

import org.codejargon.feather.Feather;

import java.util.List;

import javax.inject.Inject;

@SuppressWarnings({"Guava"})
public class AbsentView implements
        CategoriesFormView,
        CategoriesListView,
        CategoriesView {

    Logger log;

    public static AbsentView getInstance() {
        return Feather.with().instance(AbsentView.class);
    }

    @Inject
    public AbsentView(Logger log) {
        this.log = log; }

    @Override
    public void showTextInfo(String text) {
        log.info("tried to display text: " + text); }

    @Override
    public void hideProgressIndicator() {
        log.info("tried to hide progress indicator"); }

    @Override
    public void showProgressIndicator() {
        log.info("tried to show progress indicator"); }

    @Override
    public void showCategories(List<Category> categories) {
        log.info("tried to show categories: " + categories.toString()); }

    @Override
    public void showEmptyList() {
        log.info("tried to show empty list"); }

    @Override
    public void showCategory(Category category) {
        log.info("tried to show category: " + category.toString()); }
}
