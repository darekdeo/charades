package com.dariuszdeoniziak.charades.views.fragments;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.presenters.CategoriesFragmentPresenter;
import com.dariuszdeoniziak.charades.views.Layout;

import javax.inject.Inject;

import butterknife.BindView;

@Layout(R.layout.fragment_categories)
public class CategoriesFragment extends BaseFragment {

    @BindView(R.id.categories_title) TextView categoriesTitleView;
    @BindView(R.id.categories_recycler) RecyclerView categoriesRecyclerView;

    @Inject CategoriesFragmentPresenter presenter;

    void replace(CategoriesFragmentPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onTakeView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDropView();
    }
}
