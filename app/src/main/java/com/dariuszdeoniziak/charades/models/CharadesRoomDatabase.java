package com.dariuszdeoniziak.charades.models;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(
        entities = { Charade.class, Category.class },
        version = 1,
        exportSchema = false
)
public abstract class CharadesRoomDatabase extends RoomDatabase {

    public abstract CharadeDao getCharadeDao();
    public abstract CategoryDao getCategoryDao();
}
