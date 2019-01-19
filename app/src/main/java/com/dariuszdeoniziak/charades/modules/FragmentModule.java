package com.dariuszdeoniziak.charades.modules;

import android.arch.persistence.room.Room;

import com.dariuszdeoniziak.charades.models.CharadesRoomDatabase;
import com.dariuszdeoniziak.charades.models.interactors.ModelInteractor;
import com.dariuszdeoniziak.charades.models.interactors.RoomModelInteractor;
import com.dariuszdeoniziak.charades.views.fragments.BaseFragment;

import org.codejargon.feather.Provides;

import java.lang.ref.WeakReference;

import javax.inject.Singleton;


public class FragmentModule {

    private final WeakReference<BaseFragment> fragmentRef;

    public FragmentModule(BaseFragment fragment) {
        this.fragmentRef = new WeakReference<>(fragment);
    }

    @Provides
    @Singleton
    public ModelInteractor provideModelInteractor() {
        return new RoomModelInteractor(Room
                .databaseBuilder(
                        fragmentRef.get().getActivity().getApplicationContext(),
                        CharadesRoomDatabase.class,
                        "charades.db")
                .allowMainThreadQueries()
                .build());
    }
}
