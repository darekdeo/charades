package com.dariuszdeoniziak.charades.views.adapters.holders;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.data.models.Charade;
import com.dariuszdeoniziak.charades.utils.Optional;
import com.dariuszdeoniziak.charades.views.CategoriesFormContract;
import com.dariuszdeoniziak.charades.views.widgets.FontAwesomeView;

import androidx.annotation.NonNull;


public class CharadeViewHolder extends BaseViewHolder<Charade> {

    private EditText nameView;
    private FontAwesomeView deleteView;
    private Optional<CategoriesFormContract.ListItemPresenter> presenter = Optional.empty();

    public CharadeViewHolder(@NonNull View itemView) {
        super(itemView);
        nameView = itemView.findViewById(R.id.charade_name);
        deleteView = itemView.findViewById(R.id.button_delete);
    }

    @Override
    public void bind(Charade charade) {
        nameView.setText(charade.name);
        nameView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.ifPresent(l -> l.onEdited(charade, s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                deleteView.setVisibility(s.toString().isEmpty() ? View.INVISIBLE : View.GONE);
            }
        });

        deleteView.setVisibility(charade.name.isEmpty() ? View.INVISIBLE : View.GONE); // TODO probably full view model with boolean for this field should be given?
        deleteView.setOnClickListener(v -> presenter.ifPresent(l -> l.onDelete(charade)));
    }

    public void setPresenter(CategoriesFormContract.ListItemPresenter presenter) {
        this.presenter = Optional.of(presenter);
    }
}
