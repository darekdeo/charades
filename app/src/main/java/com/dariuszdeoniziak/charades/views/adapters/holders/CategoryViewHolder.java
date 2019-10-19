package com.dariuszdeoniziak.charades.views.adapters.holders;

import android.view.View;
import android.widget.TextView;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.utils.Optional;
import com.dariuszdeoniziak.charades.views.CategoriesListContract;
import com.dariuszdeoniziak.charades.views.widgets.FontAwesomeView;

import androidx.annotation.NonNull;


public class CategoryViewHolder extends BaseViewHolder<Category> {

    private View itemView;
    private TextView nameView;
    private TextView descriptionView;
    private FontAwesomeView editView;
    private FontAwesomeView deleteView;
    private Optional<CategoriesListContract.ListItemPresenter> presenter = Optional.empty();

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        nameView = itemView.findViewById(R.id.category_name);
        descriptionView = itemView.findViewById(R.id.category_description);
        editView = itemView.findViewById(R.id.button_edit);
        deleteView = itemView.findViewById(R.id.button_delete);
    }

    public void bind(Category category) {
        nameView.setText(category.name);
        descriptionView.setText(category.description);
        itemView.setOnClickListener(v -> presenter.ifPresent(l -> l.onSelect(category)));
        editView.setOnClickListener(v -> presenter.ifPresent(l -> l.onEdit(category)));
        deleteView.setOnClickListener(v -> presenter.ifPresent(l -> l.onDelete(category)));
    }

    public void setPresenter(CategoriesListContract.ListItemPresenter presenter) {
        this.presenter = Optional.of(presenter);
    }
}
