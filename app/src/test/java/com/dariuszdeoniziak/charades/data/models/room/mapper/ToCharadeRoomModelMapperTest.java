package com.dariuszdeoniziak.charades.data.models.room.mapper;

import com.dariuszdeoniziak.charades.data.models.Charade;
import com.dariuszdeoniziak.charades.data.models.room.CharadeRoomModel;

import org.junit.Assert;
import org.junit.Test;


public class ToCharadeRoomModelMapperTest {

    @Test
    public void map() {
        // given
        Charade charade = new Charade();
        charade.id = 8L;
        charade.name = "Test name";
        charade.categoryId = 4L;
        ToCharadeRoomModelMapper mapper = new ToCharadeRoomModelMapper();

        // when
        CharadeRoomModel charadeRoomModel = mapper.map(charade);

        // then
        Assert.assertEquals(charade.id, charadeRoomModel.id);
        Assert.assertEquals(charade.name, charadeRoomModel.name);
        Assert.assertEquals(charade.categoryId, charadeRoomModel.categoryId);
    }
}