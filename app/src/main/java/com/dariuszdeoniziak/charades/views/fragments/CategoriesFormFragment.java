package com.dariuszdeoniziak.charades.views.fragments;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.presenters.CategoriesFormPresenter;
import com.dariuszdeoniziak.charades.utils.AndroidStaticsWrapper;
import com.dariuszdeoniziak.charades.views.CategoriesFormView;
import com.dariuszdeoniziak.charades.views.Layout;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import trikita.knork.Knork;

@Layout(R.layout.fragment_categories_form)
public class CategoriesFormFragment extends BaseFragment implements CategoriesFormView {

    @Knork.Id(R.id.form_category_title) EditText editTextCategoryTitle;

    @Inject CategoriesFormPresenter presenter;

    int categoryId = 0;

    private static final int TYPING_DELAY = 1;
    InitialValueObservable<CharSequence> titleTextChanges;

    public static String KEY_CATEGORY_ID = "key_category_id";

    public static CategoriesFormFragment newInstance() {
        return newInstance(0);
    }

    public static CategoriesFormFragment newInstance(Integer categoryId) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_CATEGORY_ID, categoryId);

        CategoriesFormFragment fragment = new CategoriesFormFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    void replace(CategoriesFormPresenter presenter, AndroidStaticsWrapper androidWrapper) {
        this.presenter = presenter;
        this.androidWrapper = androidWrapper;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryId = getArguments().getInt(KEY_CATEGORY_ID, 0);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onTakeView(this);
        presenter.loadCategory(categoryId);
        setupViewActions();
    }

    public void setupViewActions() {
        titleTextChanges = RxTextView.textChanges(editTextCategoryTitle);
        titleTextChanges
                .debounce(TYPING_DELAY, TimeUnit.SECONDS)
                .filter(charSequence -> charSequence.length() > 0)
                .observeOn(AndroidSchedulers.mainThread())
                .map(CharSequence::toString)
                .subscribe(title -> presenter.saveCategoryTitle(title));
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onDropView();
    }

    @Override
    public void displayTextInfo(final String text) {
        androidWrapper.showToast(getActivity(), text, Toast.LENGTH_SHORT);
    }
}
