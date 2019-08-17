package com.dariuszdeoniziak.charades.data.models.room.mapper;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.data.models.room.CategoryRoomModel;

import org.junit.Assert;
import org.junit.Test;


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
        Assert.assertEquals(category.id, categoryRoomModel.id);
        Assert.assertEquals(category.name, categoryRoomModel.name);
        Assert.assertEquals(category.description, categoryRoomModel.description);
    }
}