package com.dariuszdeoniziak.charades.data.models.room.mappers;

import com.dariuszdeoniziak.charades.data.models.Charade;
import com.dariuszdeoniziak.charades.data.models.room.CharadeRoomModel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


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
        assertEquals(charade.id, charadeRoomModel.id);
        assertEquals(charade.name, charadeRoomModel.name);
        assertEquals(charade.categoryId, charadeRoomModel.categoryId);
    }
}