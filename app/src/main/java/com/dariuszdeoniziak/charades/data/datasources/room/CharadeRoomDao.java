package com.dariuszdeoniziak.charades.data.datasources.room;


import com.dariuszdeoniziak.charades.data.models.room.CharadeRoomModel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public interface CharadeRoomDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(CharadeRoomModel charade);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    int update(CharadeRoomModel charade);

    @Delete
    int delete(CharadeRoomModel charade);

    @Query("SELECT * FROM charades WHERE id=:id LIMIT 1")
    CharadeRoomModel getById(long id);

    @Query("SELECT * FROM charades")
    List<CharadeRoomModel> getAll();

    @Query("SELECT * FROM charades WHERE category_id=:categoryId")
    List<CharadeRoomModel> getAllForCategory(long categoryId);
}
