package com.dariuszdeoniziak.charades.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "categories")
public class Category {

    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo(name = "category_name")
    public String name;

    @ColumnInfo(name = "category_description")
    public String description;
}
