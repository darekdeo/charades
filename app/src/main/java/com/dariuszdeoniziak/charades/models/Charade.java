package com.dariuszdeoniziak.charades.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;


@Entity(
        tableName = "charades",
        indices = {@Index("category_id")},
        foreignKeys = @ForeignKey(
                entity = Category.class,
                parentColumns = "id",
                childColumns = "category_id",
                onDelete = CASCADE
        )
)
public class Charade {

    @PrimaryKey
    public Long id;

    @ColumnInfo(name = "charade_name")
    public String name;

    @ColumnInfo(name = "category_id")
    public Long categoryId;
}
