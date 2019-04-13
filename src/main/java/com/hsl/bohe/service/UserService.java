package com.hsl.bohe.service;

import com.hsl.bohe.common.vo.ResultVo;
import com.hsl.bohe.entity.User;

public interface UserService {
    //注册用户
    ResultVo addUser(User user);

    //通过电话号查找用户
    ResultVo login(User user);
    //检查用户的登录状态
    ResultVo check(String token);

    //用户推出
    ResultVo exit(String token);

}
