package com.dariuszdeoniziak.charades.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.views.CategoriesListContract;
import com.dariuszdeoniziak.charades.views.adapters.holders.CategoryViewHolder;

import androidx.annotation.NonNull;

public class CategoriesListAdapter extends BaseAdapter<Category, CategoryViewHolder> {

    private CategoriesListContract.ListItemPresenter presenter;

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_category, parent, false);
        CategoryViewHolder holder = new CategoryViewHolder(view);
        holder.setPresenter(presenter);
        return holder;
    }

    public void setPresenter(CategoriesListContract.ListItemPresenter presenter) {
        this.presenter = presenter;
    }
}
