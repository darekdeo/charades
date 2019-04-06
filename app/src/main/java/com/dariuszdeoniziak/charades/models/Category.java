package com.dariuszdeoniziak.charades.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "categories")
public class Category {

    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo(name = "category_name")
    public String name;

    @ColumnInfo(name = "category_description")
    public String description;
}
