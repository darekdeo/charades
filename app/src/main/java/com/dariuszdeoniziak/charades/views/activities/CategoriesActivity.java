package com.dariuszdeoniziak.charades.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.databinding.ActivityCategoriesBinding;
import com.dariuszdeoniziak.charades.presenters.CategoriesPresenter;
import com.dariuszdeoniziak.charades.utils.Logger;
import com.dariuszdeoniziak.charades.utils.Mapper;
import com.dariuszdeoniziak.charades.views.CategoriesView;
import com.dariuszdeoniziak.charades.views.CategoryScreen;
import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;
import com.dariuszdeoniziak.charades.views.fragments.CategoriesFormFragment;
import com.dariuszdeoniziak.charades.views.fragments.CategoriesListFragment;

import javax.inject.Inject;
import javax.inject.Named;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;


public class CategoriesActivity extends BaseActivity implements CategoriesView {

    @Inject CategoriesPresenter presenter;
    @Inject Logger log;
    @Inject
    @Named("to_category_screen_mapper")
    Mapper<BaseFragment, CategoryScreen> toCategoryScreenMapper;

    ActivityCategoriesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_categories);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onTakeView(this);
        binding.buttonPlus.setOnClickListener(v -> presenter.onToggleViewMode());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
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
