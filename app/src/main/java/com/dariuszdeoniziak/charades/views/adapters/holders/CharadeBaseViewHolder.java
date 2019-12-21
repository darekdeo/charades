package com.dariuszdeoniziak.charades.views.adapters.holders;

import android.view.View;

import com.dariuszdeoniziak.charades.utils.Optional;
import com.dariuszdeoniziak.charades.views.CategoriesFormContract;

import androidx.annotation.NonNull;

public abstract class CharadeBaseViewHolder<T> extends BaseViewHolder<T> {

    protected Optional<CategoriesFormContract.CharadeListItemPresenter> presenter = Optional.empty();

    CharadeBaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void setPresenter(CategoriesFormContract.CharadeListItemPresenter presenter) {
        this.presenter = Optional.of(presenter);
    }
}
