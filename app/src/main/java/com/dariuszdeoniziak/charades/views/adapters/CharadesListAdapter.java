package com.dariuszdeoniziak.charades.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.views.CategoriesFormContract;
import com.dariuszdeoniziak.charades.views.adapters.holders.CharadeBaseViewHolder;
import com.dariuszdeoniziak.charades.views.adapters.holders.CharadeViewHolder;
import com.dariuszdeoniziak.charades.views.adapters.holders.NewCharadeViewHolder;
import com.dariuszdeoniziak.charades.views.models.CharadeListItemModel;

import androidx.annotation.NonNull;

import javax.inject.Inject;

public class CharadesListAdapter extends BaseAdapter<CharadeListItemModel, CharadeBaseViewHolder<CharadeListItemModel>> {

    private CategoriesFormContract.CharadeListItemPresenter presenter;

    @Inject
    CharadesListAdapter(
            CategoriesFormContract.CharadeListItemPresenter presenter
    ) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public CharadeBaseViewHolder<CharadeListItemModel> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CharadeListItemModel.Type type = CharadeListItemModel.Type.values()[viewType];
        CharadeBaseViewHolder<CharadeListItemModel> holder;
        switch (type) {
            case CHARADE_ITEM:
                View charadeView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_charade, parent, false);
                holder = new CharadeViewHolder(charadeView);
                break;
            case NEW_ITEM:
                View newCharadeView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_new_charade, parent, false);
                holder = new NewCharadeViewHolder(newCharadeView);
                break;
            default:
                throw new IllegalArgumentException("Case not specified");
        }
        holder.setPresenter(presenter);
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).type.ordinal();
    }

}
