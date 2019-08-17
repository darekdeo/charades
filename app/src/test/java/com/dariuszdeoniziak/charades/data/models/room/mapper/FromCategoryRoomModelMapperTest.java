package com.dariuszdeoniziak.charades.data.models.room.mapper;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.data.models.room.CategoryRoomModel;

import org.junit.Assert;
import org.junit.Test;


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
        Assert.assertEquals(categoryRoomModel.id, category.id);
        Assert.assertEquals(categoryRoomModel.name, category.name);
        Assert.assertEquals(categoryRoomModel.description, category.description);
    }
}