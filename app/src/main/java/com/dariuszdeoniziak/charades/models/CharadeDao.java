package com.dariuszdeoniziak.charades.models;


import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public interface CharadeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Charade charade);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    int update(Charade charade);

    @Delete
    int delete(Charade charade);

    @Query("SELECT * FROM charades WHERE id=:id LIMIT 1")
    Charade getById(long id);

    @Query("SELECT * FROM charades")
    List<Charade> getAll();

    @Query("SELECT * FROM charades WHERE category_id=:categoryId")
    List<Charade> getAllForCategory(long categoryId);
}
