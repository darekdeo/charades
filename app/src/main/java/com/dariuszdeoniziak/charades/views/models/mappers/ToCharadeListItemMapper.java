package com.dariuszdeoniziak.charades.views.models.mappers;

import com.dariuszdeoniziak.charades.data.models.Charade;
import com.dariuszdeoniziak.charades.utils.Mapper;
import com.dariuszdeoniziak.charades.views.models.CharadeListItemModel;

public class ToCharadeListItemMapper implements Mapper<Charade, CharadeListItemModel> {

    @Override
    public CharadeListItemModel map(Charade charade) {
        CharadeListItemModel item = new CharadeListItemModel();
        item.id = charade.id;
        item.name = charade.name;
        item.type = CharadeListItemModel.Type.CHARADE_ITEM;
        return item;
    }
}
