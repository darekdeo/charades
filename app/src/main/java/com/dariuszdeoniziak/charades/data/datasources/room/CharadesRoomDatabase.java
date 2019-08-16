package com.dariuszdeoniziak.charades.data.datasources.room;

import com.dariuszdeoniziak.charades.data.models.room.CategoryRoomModel;
import com.dariuszdeoniziak.charades.data.models.room.CharadeRoomModel;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(
        entities = { CharadeRoomModel.class, CategoryRoomModel.class },
        version = 1,
        exportSchema = false
)
public abstract class CharadesRoomDatabase extends RoomDatabase {

    public abstract CharadeRoomDao getCharadeDao();
    public abstract CategoryRoomDao getCategoryDao();
}
