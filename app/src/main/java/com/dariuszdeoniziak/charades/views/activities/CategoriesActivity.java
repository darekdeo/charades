package com.dariuszdeoniziak.charades.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.presenters.CategoriesPresenter;
import com.dariuszdeoniziak.charades.utils.Logger;
import com.dariuszdeoniziak.charades.utils.Mapper;
import com.dariuszdeoniziak.charades.views.CategoriesView;
import com.dariuszdeoniziak.charades.views.CategoryScreen;
import com.dariuszdeoniziak.charades.views.ComponentsFacade;
import com.dariuszdeoniziak.charades.views.Layout;
import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;
import com.dariuszdeoniziak.charades.views.fragments.CategoriesFormFragment;
import com.dariuszdeoniziak.charades.views.fragments.CategoriesListFragment;

import javax.inject.Inject;

import trikita.knork.Knork;


@Layout(R.layout.activity_categories)
public class CategoriesActivity extends BaseActivity implements CategoriesView {

    @Inject CategoriesPresenter presenter;
    @Inject Logger log;
    @Inject Mapper<BaseFragment, CategoryScreen> toCategoryScreenMapper;

    @Knork.Id(R.id.button_plus) TextView buttonPlus;

    void replace(
            CategoriesPresenter presenter,
            ComponentsFacade componentsFacade,
            Mapper<BaseFragment, CategoryScreen> toCategoryScreenMapper
    ) {
        this.presenter = presenter;
        this.componentsFacade = componentsFacade;
        this.toCategoryScreenMapper = toCategoryScreenMapper;
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onTakeView(this);
        buttonPlus.setOnClickListener(v -> presenter.onToggleViewMode());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSave();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onDropView();
    }

    @Override
    public void showTextInfo(String text) {
        log.info("text: " + text);
        componentsFacade.showToast(text, Toast.LENGTH_SHORT);
    }

    @Override
    public void toList() {
        popOrReplaceFragment(CategoriesListFragment::newInstance, CategoriesListFragment.TAG, R.id.fragment_container);
    }

    @Override
    public void toForm() {
        replaceFragment(CategoriesFormFragment::newInstance, CategoriesFormFragment.TAG, R.id.fragment_container, true);
    }

    @Override
    public void selectCategory(Long categoryId) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    @Override
    public void editCategory(Long categoryId) {
        replaceFragment(() -> CategoriesFormFragment.newInstance(categoryId), CategoriesFormFragment.TAG, R.id.fragment_container, true);
    }

    @Override
    public CategoryScreen getCurrentScreen() {
        return toCategoryScreenMapper.map(getCurrentFragment(R.id.fragment_container));
    }
}
