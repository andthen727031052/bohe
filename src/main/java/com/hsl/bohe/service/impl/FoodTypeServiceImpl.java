package com.hsl.bohe.service.impl;

import com.hsl.bohe.common.util.ResultUtil;
import com.hsl.bohe.common.vo.ResultVo;
import com.hsl.bohe.dao.FoodTypeMapper;
import com.hsl.bohe.service.FoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodTypeServiceImpl implements FoodTypeService {

    @Autowired
    private FoodTypeMapper foodTypeMapper;
    @Override
    public ResultVo findAllType() {
        return ResultUtil.exec(true,"OK",foodTypeMapper.selectAll());
    }
}
