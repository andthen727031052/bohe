package com.hsl.bohe.controller;

import com.hsl.bohe.common.vo.FoodVo;
import com.hsl.bohe.common.vo.PageVo;
import com.hsl.bohe.common.vo.ResultVo;
import com.hsl.bohe.entity.Food;
import com.hsl.bohe.service.FoodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController
@Api(value = "食物",tags = "食物相关操作")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @ApiOperation(value = "根据食物种类分页查找列表",notes = "tid:食物类别id//page:页码，size:每页条数")
    @GetMapping("foodlist.do")
    public PageVo<FoodVo> findByInfo(int tid,int page){
        return foodService.queryByPageAndSize(tid,page,10);
    }
    @ApiOperation(value = "根据食物关键字分页查找列表",notes = "key:关键字//page:页码，size:每页条数")
    @PostMapping("listlike.do")
    public PageVo<Food> findLike(String key,Integer page) throws UnsupportedEncodingException {
            return foodService.queryByKey(URLDecoder.decode(key,"UTF-8"),page,10);
    }
    @ApiOperation(value = "根据id查找食物的全部信息")
    @GetMapping("foodbyid.do")
    public ResultVo findById(int id){
        return foodService.queryById(id);
    }



}
