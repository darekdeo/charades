package com.dariuszdeoniziak.charades.models;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Builder @Setter @Getter
@Table
public class Category {

    Long id;
    String name;
    String description;

    public List<Charade> getCharades() {
        return SugarRecord.findWithQuery(Charade.class, "select * from Charade where category = ?", String.valueOf(getId()));
    }
}
