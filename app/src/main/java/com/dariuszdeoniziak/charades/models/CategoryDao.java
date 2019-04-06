package com.dariuszdeoniziak.charades.models;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


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
