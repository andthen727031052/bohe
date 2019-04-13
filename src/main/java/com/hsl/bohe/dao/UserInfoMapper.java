package com.hsl.bohe.dao;

import com.hsl.bohe.entity.UserInfo;

public interface UserInfoMapper {
    int deleteById(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectById(Integer id);

    int updateByIdSelective(UserInfo record);

    int updateById(UserInfo record);
}