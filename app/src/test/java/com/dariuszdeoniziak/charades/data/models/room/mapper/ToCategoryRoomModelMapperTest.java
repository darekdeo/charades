package com.dariuszdeoniziak.charades.data.models.room.mapper;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.data.models.room.CategoryRoomModel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ToCategoryRoomModelMapperTest {

    @Test
    public void map() {
        // given
        Category category = new Category();
        category.id = 3L;
        category.name = "Test name";
        category.description = "Test desciption";
        ToCategoryRoomModelMapper mapper = new ToCategoryRoomModelMapper();

        // when
        CategoryRoomModel categoryRoomModel = mapper.map(category);

        // then
        assertEquals(category.id, categoryRoomModel.id);
        assertEquals(category.name, categoryRoomModel.name);
        assertEquals(category.description, categoryRoomModel.description);
    }
}