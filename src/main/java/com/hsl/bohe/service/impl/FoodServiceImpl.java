package com.hsl.bohe.service.impl;


import com.hsl.bohe.common.util.JedisUtil;
import com.hsl.bohe.common.util.ResultUtil;
import com.hsl.bohe.common.vo.FoodVo;
import com.hsl.bohe.common.vo.PageVo;
import com.hsl.bohe.common.vo.ResultVo;
import com.hsl.bohe.dao.FoodMapper;
import com.hsl.bohe.entity.Food;
import com.hsl.bohe.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodMapper foodMapper;
    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public PageVo<FoodVo> queryByPageAndSize(int tid, int page, int size) {
        PageVo<FoodVo> pageVo = new PageVo<>();
        pageVo.setPage(page);
        pageVo.setSize(size);
        long count = foodMapper.selectCount(tid);
        pageVo.setCount(count);
        int totalPage=0;
        if (count%size ==0){
            totalPage = (int) count/size;
        }else {
            totalPage = (int)count/size +1;
        }
        pageVo.setTotalpage(totalPage);
        List<FoodVo> voList = foodMapper.selectByPageAndSize(tid, (page - 1) * size, size);
        pageVo.setData(voList);
        pageVo.setCode(voList==null?0:1);
        return pageVo;
    }

    @Override
    public PageVo<Food> queryByKey(String key, int page, int size) {
        PageVo<Food> pageVo = new PageVo<>();
        pageVo.setPage(page);
        pageVo.setSize(size);

            long count = foodMapper.selectCountByKey(key);
            pageVo.setCount(count);
            int totalPage=0;
            if (count%size ==0){
                totalPage = (int) count/size;
            }else {
                totalPage = (int)count/size +1;
            }
            pageVo.setTotalpage(totalPage);
            List<Food> foodList = foodMapper.selectByKey(key, (page - 1) * size, size);
            pageVo.setData(foodList);
            pageVo.setCode(foodList==null?0:1);

        return pageVo;
    }

    @Override
    public ResultVo queryById(int id) {
        return ResultUtil.exec(true,"OK",foodMapper.selectOneById(id));
    }
}
