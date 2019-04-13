package com.hsl.bohe.dao;

import com.hsl.bohe.entity.TypeFood;

public interface TypeFoodMapper {
    int deleteById(Integer id);

    int insert(TypeFood record);

    int insertSelective(TypeFood record);

    TypeFood selectById(Integer id);

    int updateByIdSelective(TypeFood record);

    int updateById(TypeFood record);
}