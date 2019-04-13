package com.hsl.bohe.controller;

import com.hsl.bohe.common.syscount.SystemCon;
import com.hsl.bohe.common.util.ResultUtil;
import com.hsl.bohe.common.vo.ResultVo;
import com.hsl.bohe.entity.User;
import com.hsl.bohe.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "用户",tags = "用户相关操作")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login.do")
    @ApiOperation(value = "用户登录",notes = "传入用户信息")
    public ResultVo login(User user, HttpServletRequest request, HttpServletResponse response){
        Cookie[] cks=request.getCookies();
        String token="";
        ResultVo resultVo=null;
        for (Cookie c:cks) {
            if (c.getName().equals(SystemCon.COOKIETOKEN)){
                token = c.getValue();
                break;
            }
        }
        if (token.length()>0){
            resultVo=userService.check(token);
        }else {
            resultVo=userService.login(user);
            if (resultVo.getCode()==1){
                Cookie cookie=new Cookie(SystemCon.COOKIETOKEN,resultVo.getData().toString());
                cookie.setPath("/");
                cookie.setDomain("");
                //设置跨域共享cookie
                response.addCookie(cookie);
            }
        }
        return resultVo;
    }
    @PostMapping("userreg.do")
    @ApiOperation(value = "用户注册",notes = "注册信息")
    public ResultVo register(User user){
        return userService.addUser(user);
    }

    //检验登录有效性
    @ApiOperation("用户有效性检验")
    @GetMapping("usercheck.do")
    public ResultVo check(HttpServletRequest request){
        Cookie[] cks = request.getCookies();
        String token = "";
        ResultVo resultVo=null;
        for (Cookie c:cks) {
            if (c.getName().equals(SystemCon.COOKIETOKEN)){
                token=c.getValue();
                break;
            }
        }
        if (token.length()>0){
            resultVo=userService.check(token);
        }else{
            resultVo=ResultUtil.exec(false,"登陆无效",null);
        }
        return resultVo;
    }

    @ApiOperation("退出登录")
    @GetMapping("userexit.do")
    public ResultVo exit(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cks = request.getCookies();
        String token="";
        ResultVo resultVo = null;
        for (Cookie c: cks) {
            if (c.getName().equals(SystemCon.COOKIETOKEN)){
                token=c.getValue();
                break;
            }
        }
        if (token.length()>0){
            //删除Redis 删除cookie
            userService.exit(token);
            Cookie cookie=new Cookie(SystemCon.COOKIETOKEN,"");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return ResultUtil.exec(true,"OK",null);
    }
}
