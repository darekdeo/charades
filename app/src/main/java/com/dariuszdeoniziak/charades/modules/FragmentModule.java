package com.dariuszdeoniziak.charades.modules;

import com.dariuszdeoniziak.charades.data.datasources.CharadesDataSource;
import com.dariuszdeoniziak.charades.data.datasources.room.CharadesRoomDataSource;
import com.dariuszdeoniziak.charades.data.datasources.room.CharadesRoomDatabase;
import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;

import org.codejargon.feather.Provides;

import java.lang.ref.WeakReference;

import javax.inject.Singleton;

import androidx.room.Room;


public class FragmentModule {

    private final WeakReference<BaseFragment> fragmentRef;

    public FragmentModule(BaseFragment fragment) {
        this.fragmentRef = new WeakReference<>(fragment);
    }

    @Provides
    @Singleton
    public CharadesDataSource provideModelInteractor() {
        return new CharadesRoomDataSource(Room
                .databaseBuilder(
                        fragmentRef.get().getActivity().getApplicationContext(),
                        CharadesRoomDatabase.class,
                        "charades.db")
                .allowMainThreadQueries()
                .build());
    }
}
