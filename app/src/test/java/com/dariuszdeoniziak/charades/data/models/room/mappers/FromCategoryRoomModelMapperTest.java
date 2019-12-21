package com.dariuszdeoniziak.charades.data.models.room.mappers;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.data.models.room.CategoryRoomModel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class FromCategoryRoomModelMapperTest {

    @Test
    public void map() {
        // given
        CategoryRoomModel categoryRoomModel = new CategoryRoomModel();
        categoryRoomModel.id = 5L;
        categoryRoomModel.name = "Test name";
        categoryRoomModel.description = "Test description";
        FromCategoryRoomModelMapper mapper = new FromCategoryRoomModelMapper();

        // when
        Category category = mapper.map(categoryRoomModel);

        // then
        assertEquals(categoryRoomModel.id, category.id);
        assertEquals(categoryRoomModel.name, category.name);
        assertEquals(categoryRoomModel.description, category.description);
    }
}