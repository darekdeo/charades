package com.dariuszdeoniziak.charades.views.models;

public class CharadeListItemModel {
    public long id;
    public String name;
    public CharadeListItemModel.Type type;

    public enum Type {
        NEW_ITEM,
        CHARADE_ITEM
    }
}
