package com.dariuszdeoniziak.charades.views.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.databinding.FragmentCategoriesFormBinding;
import com.dariuszdeoniziak.charades.presenters.CategoriesFormPresenter;
import com.dariuszdeoniziak.charades.views.CategoriesFormContract;
import com.dariuszdeoniziak.charades.views.adapters.CharadesListAdapter;
import com.dariuszdeoniziak.charades.views.models.CategoriesFormModel;
import com.dariuszdeoniziak.charades.views.models.CharadeListItemModel;

import java.util.List;

import javax.inject.Inject;


public class CategoriesFormFragment extends BaseFragment implements CategoriesFormContract.View {

    private final CategoriesFormPresenter presenter;
    private final CharadesListAdapter charadesListAdapter;

    private FragmentCategoriesFormBinding binding;
    private final static String KEY_CATEGORY_ID = "key_category_id";

    public static String TAG = CategoriesFormFragment.class.getSimpleName();

    @Inject
    CategoriesFormFragment(
            CategoriesFormPresenter presenter,
            CharadesListAdapter charadesListAdapter
    ) {
        this.presenter = presenter;
        this.charadesListAdapter = charadesListAdapter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCategoriesFormBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.formCharadesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.formCharadesRecycler.setAdapter(charadesListAdapter);
        binding.formCategoryTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.onSaveCategoryTitle(s.toString());
            }
        });
        binding.formCharadesCloseButton.setOnClickListener(v -> presenter.onClose());
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onTakeView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onDropView();
    }

    @Override
    public void setup(CategoriesFormModel model) {
        binding.setModel(model);
        binding.setPresenter(presenter);
        binding.invalidateAll();
    }

    @Override
    public void showTextInfo(final String text) {
    }

    @Override
    public void showCategory(Category category) {
        binding.formCategoryTitle.setText(category.name);
    }

    @Override
    public void showCharades(List<CharadeListItemModel> charades) {
        charadesListAdapter.adapt(charades);
    }
}
