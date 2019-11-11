package com.dariuszdeoniziak.charades.modules;

import com.dariuszdeoniziak.charades.data.datasources.CharadesDataSource;
import com.dariuszdeoniziak.charades.data.datasources.LabelsDataSource;
import com.dariuszdeoniziak.charades.data.datasources.resourcelabels.ResourcesLabelsDataSource;
import com.dariuszdeoniziak.charades.data.datasources.room.CharadesRoomDataSource;
import com.dariuszdeoniziak.charades.data.datasources.room.CharadesRoomDatabase;
import com.dariuszdeoniziak.charades.data.models.room.mapper.FromCategoryRoomModelMapper;
import com.dariuszdeoniziak.charades.data.models.room.mapper.FromCharadeRoomModelMapper;
import com.dariuszdeoniziak.charades.data.models.room.mapper.ToCategoryRoomModelMapper;
import com.dariuszdeoniziak.charades.data.models.room.mapper.ToCharadeRoomModelMapper;
import com.dariuszdeoniziak.charades.data.repositories.CharadesRepository;
import com.dariuszdeoniziak.charades.data.repositories.CharadesRepositoryImpl;
import com.dariuszdeoniziak.charades.data.repositories.LabelsRepository;
import com.dariuszdeoniziak.charades.data.repositories.LabelsRepositoryImpl;
import com.dariuszdeoniziak.charades.schedulers.DefaultSchedulerFactory;
import com.dariuszdeoniziak.charades.schedulers.SchedulerFactory;
import com.dariuszdeoniziak.charades.views.ComponentsFacade;
import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;

import org.codejargon.feather.Provides;

import java.lang.ref.WeakReference;
import java.util.Objects;

import javax.inject.Singleton;

import androidx.room.Room;


public class FragmentModule {

    private final WeakReference<BaseFragment> fragmentRef;

    public FragmentModule(BaseFragment fragment) {
        this.fragmentRef = new WeakReference<>(fragment);
    }

    @Provides
    @Singleton
    public CharadesDataSource provideCharadesDataSource() {
        return new CharadesRoomDataSource(Room
                .databaseBuilder(
                        Objects.requireNonNull(fragmentRef.get().getContext()).getApplicationContext(),
                        CharadesRoomDatabase.class,
                        "charades.db")
                .allowMainThreadQueries()
                .build());
    }

    @Provides
    @Singleton
    public CharadesRepository provideCharadesRepository() {
        return new CharadesRepositoryImpl(
                provideCharadesDataSource(),
                new ToCategoryRoomModelMapper(),
                new FromCategoryRoomModelMapper(),
                new ToCharadeRoomModelMapper(),
                new FromCharadeRoomModelMapper()
        );
    }

    @Provides
    @Singleton
    public LabelsDataSource provideResourcesLabelsDataSource() {
        return new ResourcesLabelsDataSource(Objects.requireNonNull(fragmentRef.get().getContext()));
    }

    @Provides
    @Singleton
    public LabelsRepository provideLabelsRepository() {
        return new LabelsRepositoryImpl(provideResourcesLabelsDataSource());
    }

    @Provides
    @Singleton
    public ComponentsFacade provideComponentsFacade() {
        return new ComponentsFacade(fragmentRef.get().getContext());
    }

    @Provides
    @Singleton
    public SchedulerFactory provideSchedulerProvider() {
        return new DefaultSchedulerFactory();
    }
}
