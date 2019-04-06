package com.dariuszdeoniziak.charades.views.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.models.Category;
import com.dariuszdeoniziak.charades.presenters.CategoriesFormPresenter;
import com.dariuszdeoniziak.charades.utils.AndroidStaticsWrapper;
import com.dariuszdeoniziak.charades.utils.Optional;
import com.dariuszdeoniziak.charades.views.CategoriesFormView;
import com.dariuszdeoniziak.charades.views.Layout;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import trikita.knork.Knork;


@SuppressLint("CheckResult")
@Layout(R.layout.fragment_categories_form)
public class CategoriesFormFragment extends BaseFragment implements CategoriesFormView {

    @Knork.Id(R.id.form_category_title) EditText editTextCategoryTitle;

    @Inject CategoriesFormPresenter presenter;

    long categoryId = 0;

    private final static String KEY_CATEGORY_ID = "key_category_id";
    private static final int TYPING_DELAY = 1;

    private Optional<Disposable> titleTextChangesDisposable = Optional.empty();

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

    private void setupViewActions() {
        titleTextChangesDisposable = Optional.of(RxTextView
                .textChanges(editTextCategoryTitle)
                .subscribeOn(Schedulers.io())
                .debounce(TYPING_DELAY, TimeUnit.SECONDS)
                .filter(charSequence -> charSequence.length() > 0)
                .observeOn(AndroidSchedulers.mainThread())
                .map(CharSequence::toString)
                .subscribe(title -> presenter.saveCategoryTitle(title)));
    }

    @Override
    public void onStop() {
        super.onStop();
        titleTextChangesDisposable.ifPresent(Disposable::dispose);
        presenter.onDropView();
    }

    @Override
    public void showTextInfo(final String text) {
        androidWrapper.showToast(getActivity(), text, Toast.LENGTH_SHORT);
    }

    @Override
    public void showCategory(Category category) {
        // TODO: show category and write test
    }
}
