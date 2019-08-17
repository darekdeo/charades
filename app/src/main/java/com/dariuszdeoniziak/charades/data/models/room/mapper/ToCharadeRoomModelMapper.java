package com.dariuszdeoniziak.charades.data.models.room.mapper;

import com.dariuszdeoniziak.charades.data.models.Charade;
import com.dariuszdeoniziak.charades.data.models.room.CharadeRoomModel;
import com.dariuszdeoniziak.charades.utils.Mapper;


public class ToCharadeRoomModelMapper implements Mapper<Charade, CharadeRoomModel> {

    @Override
    public CharadeRoomModel map(Charade charade) {
        CharadeRoomModel charadeRoomModel = new CharadeRoomModel();
        charadeRoomModel.id = charade.id;
        charadeRoomModel.name = charade.name;
        charadeRoomModel.categoryId = charade.categoryId;
        return charadeRoomModel;
    }
}
