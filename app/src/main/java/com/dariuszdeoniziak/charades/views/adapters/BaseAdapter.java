package com.dariuszdeoniziak.charades.views.adapters;

import com.dariuszdeoniziak.charades.views.adapters.holders.BaseViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


abstract public class BaseAdapter<T, VH extends BaseViewHolder<T>> extends RecyclerView.Adapter<VH> {

    private List<T> items;

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        T item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        else
            return items.size();
    }

    public void adapt(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    T getItem(int position) {
        return items.get(position);
    }
}
