package com.dariuszdeoniziak.charades.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.views.adapters.holders.CategoryViewHolder;

import androidx.annotation.NonNull;

public class CategoriesListAdapter extends BaseAdapter<Category, CategoryViewHolder> {

    private CategoryViewHolder.CategoryClickListener categoryClickListener;

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_category, parent, false);
        CategoryViewHolder holder = new CategoryViewHolder(view);
        holder.setCategoryClickListener(categoryClickListener);
        return holder;
    }

    public void setCategoryClickListener(CategoryViewHolder.CategoryClickListener listener) {
        categoryClickListener = listener;
    }
}
