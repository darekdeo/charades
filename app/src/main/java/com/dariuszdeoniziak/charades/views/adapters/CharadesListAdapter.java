package com.dariuszdeoniziak.charades.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.data.models.Charade;
import com.dariuszdeoniziak.charades.views.CategoriesFormContract;
import com.dariuszdeoniziak.charades.views.adapters.holders.CharadeViewHolder;

import androidx.annotation.NonNull;

public class CharadesListAdapter extends BaseAdapter<Charade, CharadeViewHolder> {

    private CategoriesFormContract.ListItemPresenter presenter;

    @NonNull
    @Override
    public CharadeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_category, parent, false);
        CharadeViewHolder holder = new CharadeViewHolder(view);
        holder.setPresenter(presenter);
        return holder;
    }

    public void setPresenter(CategoriesFormContract.ListItemPresenter presenter) {
        this.presenter = presenter;
    }
}
