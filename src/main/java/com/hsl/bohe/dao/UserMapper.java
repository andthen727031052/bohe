package com.hsl.bohe.dao;

import com.hsl.bohe.entity.User;

public interface UserMapper {
    int deleteById(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectById(Integer id);
    //通过电话查找用户
    User selectByPhone(String phone);

    int updateByIdSelective(User record);

    int updateById(User record);
}