package com.hsl.bohe.service;



import com.hsl.bohe.common.vo.FoodVo;
import com.hsl.bohe.common.vo.PageVo;
import com.hsl.bohe.common.vo.ResultVo;
import com.hsl.bohe.entity.Food;


public interface FoodService {
    //根据页数和每页条数分页查找指定类型的食物列表
    PageVo<FoodVo> queryByPageAndSize(int tid, int page, int size);

    //根据关键字分页搜索食物列表
    PageVo<Food> queryByKey(String key,int page,int size);
    //根据id查询食物的详细信息
    ResultVo queryById(int id);
}
