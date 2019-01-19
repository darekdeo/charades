package com.dariuszdeoniziak.charades.models;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


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
