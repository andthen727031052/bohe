package com.hsl.bohe.service.impl;


import com.alibaba.fastjson.JSON;
import com.hsl.bohe.common.syscount.SystemCon;
import com.hsl.bohe.common.util.JedisUtil;
import com.hsl.bohe.common.util.PassUtil;
import com.hsl.bohe.common.util.ResultUtil;
import com.hsl.bohe.common.util.TokenUtil;
import com.hsl.bohe.common.vo.ResultVo;
import com.hsl.bohe.dao.UserMapper;
import com.hsl.bohe.entity.User;
import com.hsl.bohe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public ResultVo addUser(User user) {
        User user1 = userMapper.selectByPhone(user.getPhone());
        if (user1!=null){
           return ResultUtil.exec(false,"ERROR","电话号已经注册");
        }else{
            user.setPassword(PassUtil.base64Enc(user.getPassword(),"UTF-8"));
            return ResultUtil.exec(userMapper.insert(user)>0,null,null);
        }
    }

    @Override
    public ResultVo login(User user) {
        User user1 = userMapper.selectByPhone(user.getPhone());
        if (user1==null){
          return  ResultUtil.exec(false,"ERROR","该账号不存在");
        }else if (!Objects.equals(PassUtil.base64Dec(user1.getPassword(),"UTF-8"),user.getPassword())){
            return ResultUtil.exec(false,"ERROR","密码错误");
        }else {
            //验证成功
            //token id msg和随机数组成的字符串
            String token = TokenUtil.createToken(user1.getId(),user.getPhone());
            jedisUtil.save(SystemCon.PHONETOKEN,user.getPhone(),token);
            jedisUtil.save(SystemCon.LOGINTOKEN,token,JSON.toJSONString(user1));
            return ResultUtil.exec(true,"OK",token);
        }

    }

    @Override
    public ResultVo check(String token) {
        if(jedisUtil.isHave(SystemCon.LOGINTOKEN,token)){
            return ResultUtil.exec(true,"OK",JSON.parseObject(jedisUtil.get(SystemCon.LOGINTOKEN,token),User.class));
        }
        return ResultUtil.exec(false,"登录失效",null);
    }

    @Override
    public ResultVo exit(String token) {
        if (jedisUtil.isHave(SystemCon.LOGINTOKEN,token)){
            //存在 删除
            jedisUtil.del(SystemCon.LOGINTOKEN,token);
            String msg=PassUtil.base64Dec(token,"UTF-8");
            String[] arr = msg.split(",");
            jedisUtil.del(SystemCon.PHONETOKEN,arr[1]);
            return ResultUtil.exec(true,"OK","您已退出");
        }
        return null;
    }


}
