package com.dariuszdeoniziak.charades.models;

import com.orm.dsl.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Setter @Getter
@Table
public class Charade {

    Long id;
    Long category;
    String name;
}
