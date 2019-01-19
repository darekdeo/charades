package com.dariuszdeoniziak.charades.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;


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
