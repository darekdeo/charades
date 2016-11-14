package com.dariuszdeoniziak.charades.models;

import com.orm.SugarRecord;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Builder
public class Charade extends SugarRecord {

    String name;
}
