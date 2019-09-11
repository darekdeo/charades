package com.dariuszdeoniziak.charades.views.adapters.holders;

import android.view.View;
import android.widget.TextView;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.utils.Optional;
import com.dariuszdeoniziak.charades.views.widgets.FontAwesomeView;

import androidx.annotation.NonNull;


public class CategoryViewHolder extends BaseViewHolder<Category> {

    private TextView nameView;
    private TextView descriptionView;
    private FontAwesomeView editView;
    private FontAwesomeView deleteView;
    private Optional<CategoryClickListener> categoryClickListener = Optional.empty();

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        nameView = itemView.findViewById(R.id.category_name);
        descriptionView = itemView.findViewById(R.id.category_description);
        editView = itemView.findViewById(R.id.button_edit);
        deleteView = itemView.findViewById(R.id.button_delete);
    }

    public void bind(Category category) {
        nameView.setText(category.name);
        descriptionView.setText(category.description);
        editView.setOnClickListener(v -> categoryClickListener.ifPresent(l -> l.edit(category)));
        deleteView.setOnClickListener(v -> categoryClickListener.ifPresent(l -> l.delete(category)));
    }

    public void setCategoryClickListener(CategoryClickListener listener) {
        categoryClickListener = Optional.of(listener);
    }

    public interface CategoryClickListener {

        void edit(Category category);

        void delete(Category category);
    }
}
