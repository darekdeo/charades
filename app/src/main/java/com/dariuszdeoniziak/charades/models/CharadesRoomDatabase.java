package com.dariuszdeoniziak.charades.models;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(
        entities = { Charade.class, Category.class },
        version = 1,
        exportSchema = false
)
public abstract class CharadesRoomDatabase extends RoomDatabase {

    public abstract CharadeDao getCharadeDao();
    public abstract CategoryDao getCategoryDao();
}
