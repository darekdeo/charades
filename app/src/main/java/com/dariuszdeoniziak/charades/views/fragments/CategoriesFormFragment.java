package com.dariuszdeoniziak.charades.views.fragments;

import android.widget.EditText;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.presenters.CategoriesFormPresenter;
import com.dariuszdeoniziak.charades.views.CategoriesFormView;
import com.dariuszdeoniziak.charades.views.Layout;
import com.jakewharton.rxbinding2.widget.RxTextView;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import trikita.knork.Knork;

@Layout(R.layout.fragment_categories_form)
public class CategoriesFormFragment extends BaseFragment implements CategoriesFormView {

    @Knork.Id(R.id.form_category_title) EditText editTextCategoryTitle;

    @Inject CategoriesFormPresenter presenter;

    public static CategoriesFormFragment newInstance() {
        return new CategoriesFormFragment();
    }

    void replace(CategoriesFormPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onTakeView(this);

        RxTextView.textChanges(editTextCategoryTitle)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        String title = charSequence.toString();
                        presenter.onTitleEdited(title);
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDropView();
    }
}
