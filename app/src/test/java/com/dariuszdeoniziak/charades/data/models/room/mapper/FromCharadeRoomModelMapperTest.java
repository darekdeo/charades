package com.dariuszdeoniziak.charades.data.models.room.mapper;

import com.dariuszdeoniziak.charades.data.models.Charade;
import com.dariuszdeoniziak.charades.data.models.room.CharadeRoomModel;

import org.junit.Assert;
import org.junit.Test;


public class FromCharadeRoomModelMapperTest {

    @Test
    public void map() {
        // given
        CharadeRoomModel charadeRoomModel = new CharadeRoomModel();
        charadeRoomModel.id = 10L;
        charadeRoomModel.name = "Test name";
        charadeRoomModel.categoryId = 2L;
        FromCharadeRoomModelMapper mapper = new FromCharadeRoomModelMapper();

        // when
        Charade charade = mapper.map(charadeRoomModel);

        // then
        Assert.assertEquals(charadeRoomModel.id, charade.id);
        Assert.assertEquals(charadeRoomModel.name, charade.name);
        Assert.assertEquals(charadeRoomModel.categoryId, charade.categoryId);
    }
}