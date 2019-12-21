package com.dariuszdeoniziak.charades.modules;

import com.dariuszdeoniziak.charades.data.models.Charade;
import com.dariuszdeoniziak.charades.utils.Mapper;
import com.dariuszdeoniziak.charades.views.CategoryScreen;
import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;
import com.dariuszdeoniziak.charades.views.models.CharadeListItemModel;
import com.dariuszdeoniziak.charades.views.models.mappers.ToCategoryScreenMapper;
import com.dariuszdeoniziak.charades.views.models.mappers.ToCharadeListItemMapper;

import org.codejargon.feather.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

public class MappersModule {

    @Provides
    @Named("to_category_screen_mapper")
    @Singleton
    public Mapper<BaseFragment, CategoryScreen> provideToCategoryScreenMapper() {
        return new ToCategoryScreenMapper();
    }

    @Provides
    @Named("to_charade_list_item_model_mapper")
    @Singleton
    public Mapper<Charade, CharadeListItemModel> provideToCharadeListItemMapper() {
        return new ToCharadeListItemMapper();
    }
}
