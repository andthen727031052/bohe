package com.hsl.bohe.dao;

import com.hsl.bohe.common.vo.FoodVo;
import com.hsl.bohe.entity.Food;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FoodMapper {
    int deleteById(Integer id);

    int insert(Food record);

    int insertSelective(Food record);

    Food selectById(Integer id);
    FoodVo selectOneById(int id);

    int updateByIdSelective(Food record);

    int updateById(Food record);
    //根据食物分类分页查询相关食物的信息
    List<FoodVo> selectByPageAndSize(@Param("tid") int tid,@Param("from")int from,@Param("size")int size);

    //查询所有列表
    List<Food> selectAll();
    //查询指定类型食物的总数
    long selectCount(int tid);
    //查询名称相关食物的总数
    long selectCountByKey(String key);
    //根据关键字分页查询
    List<Food> selectByKey(@Param("key") String key,@Param("from")int from,@Param("size")int size);
}