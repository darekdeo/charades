package com.dariuszdeoniziak.charades.views.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.presenters.CategoriesFormPresenter;
import com.dariuszdeoniziak.charades.utils.Optional;
import com.dariuszdeoniziak.charades.views.CategoriesFormView;
import com.dariuszdeoniziak.charades.views.ComponentsFacade;
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

    public static String TAG = CategoriesFormFragment.class.getSimpleName();

    public static CategoriesFormFragment newInstance() {
        return newInstance(0L);
    }

    public static CategoriesFormFragment newInstance(Long categoryId) {
        Bundle bundle = new Bundle();
        bundle.putLong(KEY_CATEGORY_ID, categoryId);

        CategoriesFormFragment fragment = new CategoriesFormFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    void replace(CategoriesFormPresenter presenter, ComponentsFacade componentsFacade) {
        this.presenter = presenter;
        this.componentsFacade = componentsFacade;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryId = getArguments().getLong(KEY_CATEGORY_ID, 0);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onTakeView(this);
        presenter.onLoadCategory(categoryId); // TODO move to presenter
        setupViewActions();
    }

    private void setupViewActions() {
        titleTextChangesDisposable = Optional.of(RxTextView // TODO move rx to presenter, view should not have such logic
                .textChanges(editTextCategoryTitle)
                .subscribeOn(Schedulers.io())
                .debounce(TYPING_DELAY, TimeUnit.SECONDS)
                .filter(charSequence -> charSequence.length() > 0)
                .observeOn(AndroidSchedulers.mainThread())
                .map(CharSequence::toString)
                .subscribe(title -> presenter.onSaveCategoryTitle(title)));
    }

    @Override
    public void onStop() {
        super.onStop();
        titleTextChangesDisposable.ifPresent(Disposable::dispose);
        presenter.onDropView();
    }

    @Override
    public void showTextInfo(final String text) {
        componentsFacade.showToast(text, Toast.LENGTH_SHORT);
    }

    @Override
    public void showCategory(Category category) {
        // TODO show category and write test
    }
}
