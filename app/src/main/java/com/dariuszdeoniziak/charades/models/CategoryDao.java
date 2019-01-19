package com.dariuszdeoniziak.charades.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Category category);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    int update(Category category);

    @Delete
    int delete(Category category);

    @Query("SELECT * FROM categories WHERE id=:id LIMIT 1")
    Category getById(long id);

    @Query("SELECT * FROM categories")
    List<Category> getAll();
}
