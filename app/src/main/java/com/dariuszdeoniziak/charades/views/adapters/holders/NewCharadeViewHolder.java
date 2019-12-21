package com.dariuszdeoniziak.charades.views.adapters.holders;

import android.view.View;
import android.widget.Button;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.views.CategoriesFormContract;
import com.dariuszdeoniziak.charades.views.models.CharadeListItemModel;

import androidx.annotation.NonNull;

public class NewCharadeViewHolder extends CharadeBaseViewHolder<CharadeListItemModel> {

    private Button buttonView;

    public NewCharadeViewHolder(@NonNull View itemView) {
        super(itemView);
        buttonView = itemView.findViewById(R.id.charade_button_new);
    }

    @Override
    public void bind(CharadeListItemModel item) {
        buttonView.setOnClickListener(v -> presenter.ifPresent(CategoriesFormContract.CharadeListItemPresenter::onNew));
    }
}
