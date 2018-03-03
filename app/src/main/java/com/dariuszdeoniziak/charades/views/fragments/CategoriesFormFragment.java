package com.dariuszdeoniziak.charades.views.fragments;

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

    private static final int TYPING_DELAY = 1;
    InitialValueObservable<CharSequence> titleTextChanges;

    public static CategoriesFormFragment newInstance() {
        return new CategoriesFormFragment();
    }

    void replace(CategoriesFormPresenter presenter, AndroidStaticsWrapper androidWrapper) {
        this.presenter = presenter;
        this.androidWrapper = androidWrapper;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onTakeView(this);
        setupViewActions();
    }

    public void setupViewActions() {
        titleTextChanges = RxTextView.textChanges(editTextCategoryTitle);
        titleTextChanges
                .debounce(TYPING_DELAY, TimeUnit.SECONDS)
                .filter(charSequence -> charSequence.length() > 0)
                .observeOn(AndroidSchedulers.mainThread())
                .map(CharSequence::toString)
                .subscribe(title -> presenter.onTitleEdited(title));
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
