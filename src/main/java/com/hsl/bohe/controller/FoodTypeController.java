package com.hsl.bohe.controller;

import com.hsl.bohe.common.vo.ResultVo;
import com.hsl.bohe.service.FoodTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "食物分类",tags = "分类相关的操作")
public class FoodTypeController {
    @Autowired
    private FoodTypeService foodTypeService;

    @GetMapping("typelist.do")
    @ApiOperation(value = "显示所有食物分类",notes = "查询所有食物种类")
    public ResultVo showAllType(){
       return foodTypeService.findAllType();
    }


}
