package com.dariuszdeoniziak.charades.views.adapters.holders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


abstract public class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    abstract public void bind(T item);
}
