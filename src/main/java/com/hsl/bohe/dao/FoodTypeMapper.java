package com.hsl.bohe.dao;

import com.hsl.bohe.entity.FoodType;

import java.util.List;

public interface FoodTypeMapper {
    int deleteById(Integer id);

    int insert(FoodType record);

    int insertSelective(FoodType record);

    FoodType selectById(Integer id);

    int updateByIdSelective(FoodType record);

    int updateById(FoodType record);

    //获取食物分类列表
    List<FoodType> selectAll();
}