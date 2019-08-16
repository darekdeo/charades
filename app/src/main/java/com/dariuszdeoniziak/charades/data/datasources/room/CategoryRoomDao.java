package com.dariuszdeoniziak.charades.data.datasources.room;

import com.dariuszdeoniziak.charades.data.models.room.CategoryRoomModel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public interface CategoryRoomDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(CategoryRoomModel category);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    int update(CategoryRoomModel category);

    @Delete
    int delete(CategoryRoomModel category);

    @Query("SELECT * FROM categories WHERE id=:id LIMIT 1")
    CategoryRoomModel getById(long id);

    @Query("SELECT * FROM categories")
    List<CategoryRoomModel> getAll();
}
