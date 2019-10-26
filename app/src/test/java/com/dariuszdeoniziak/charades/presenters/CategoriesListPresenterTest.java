package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.data.models.Label;
import com.dariuszdeoniziak.charades.data.repositories.CharadesRepository;
import com.dariuszdeoniziak.charades.data.repositories.LabelsRepository;
import com.dariuszdeoniziak.charades.utils.RxJavaTestRunner;
import com.dariuszdeoniziak.charades.views.CategoriesListContract;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(RxJavaTestRunner.class)
public class CategoriesListPresenterTest {

    @Mock List<Category> categories;
    @Mock CategoriesListContract.View view;
    @Mock CharadesRepository charadesRepository;
    @Mock LabelsRepository labelsRepository;
    private CategoriesListPresenter presenter;

    @Before
    public void setUp() {
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());

        MockitoAnnotations.initMocks(this);
        presenter = new CategoriesListPresenter(charadesRepository, labelsRepository);
        presenter.onTakeView(view);
    }

    @After
    public void tearDown() {
        RxJavaPlugins.reset();
        reset(categories, view, charadesRepository, labelsRepository);
    }

    @Test
    public void loadCategoriesCallsShowCategories() {
        // given
        when(charadesRepository.getCategories())
                .thenReturn(Single.just(categories));

        // when
        presenter.onLoadCategories();

        // then
        verify(charadesRepository).getCategories();
        verify(view).showProgressIndicator();
        verify(view).showCategories(categories);
        verify(view).hideProgressIndicator();
    }

    @Test
    public void loadCategoriesCallsShowEmptyList() {
        // given
        when(charadesRepository.getCategories())
                .thenReturn(Single.just(Collections.emptyList()));

        // when
        presenter.onLoadCategories();

        // then
        verify(charadesRepository).getCategories();
        verify(view).showProgressIndicator();
        verify(view).showEmptyList();
        verify(view).hideProgressIndicator();
    }

    @Test
    public void selectCategoryCallsRouteToGame() {
        // given
        Category category = new Category();
        category.id = 5L;

        // when
        presenter.onSelect(category);

        // then
        verify(view).selectCategory(category.id);
    }

    @Test
    public void editCategoryCallsRouteToForm() {
        // given
        Category category = new Category();
        category.id = 5L;

        // when
        presenter.onEdit(category);

        // then
        verify(view).editCategory(category.id);
    }

    @Test
    public void deleteCategoryCallsShowConfirmDialog() {
        // given
        Category category = new Category();
        when(labelsRepository.getLabel(Label.categories_list_dialog_confirm_delete_title)).thenReturn("title");
        when(labelsRepository.getLabel(Label.categories_list_dialog_confirm_delete_message)).thenReturn("message");
        when(labelsRepository.getLabel(Label.yes)).thenReturn("yes");
        when(labelsRepository.getLabel(Label.no)).thenReturn("no");

        // when
        presenter.onDelete(category);

        // then
        verify(view).showConfirmDeleteCategory(
                category,
                labelsRepository.getLabel(Label.categories_list_dialog_confirm_delete_title),
                labelsRepository.getLabel(Label.categories_list_dialog_confirm_delete_message, category.name),
                labelsRepository.getLabel(Label.yes),
                labelsRepository.getLabel(Label.no)
        );
    }

    @Test
    public void confirmDeleteCategoryCallsDeleteCategoryAndLoadCategories() {
        // given
        Category category = new Category();
        when(charadesRepository.deleteCategory(category))
                .thenReturn(Single.just(1L));
        when(charadesRepository.getCategories())
                .thenReturn(Single.just(categories));

        // when
        presenter.onConfirmDeleteCategory(category);

        // then
        verify(charadesRepository).deleteCategory(category);
        verify(charadesRepository).getCategories();
        verify(view).showProgressIndicator();
        verify(view).showCategories(categories);
        verify(view).hideProgressIndicator();
    }
}