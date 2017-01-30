package com.dariuszdeoniziak.charades.models;

import com.orm.SugarRecord;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Builder
public class Category extends SugarRecord {

    String name;
    String description;

    public List<Charade> getCharades() {
        return Charade.find(Charade.class, "category = ?", String.valueOf(getId()));
    }
}
