package com.chrisyoung.appserver.controller;


import com.chrisyoung.appserver.constant.ResultCode;
import com.chrisyoung.appserver.domain.AppUser;
import com.chrisyoung.appserver.domain.UserAuths;
import com.chrisyoung.appserver.dto.Result;
import com.chrisyoung.appserver.service.impl.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-11-06 11:32
 * @description: 注册接口
 **/

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "注册新用户")
    @ApiImplicitParam(name = "userAuths",value = "用户权限对象",dataType = "UserAuths")
    @RequestMapping(value = "/usr/register",method = RequestMethod.POST)
    public Result register(@RequestBody UserAuths userAuths){
        Boolean result=userService.registerUser(userAuths);

        if(result){
            return Result.success();
        }else{
            return Result.failure(ResultCode.DATA_IS_WRONG);
        }
    }

    @ApiOperation(value ="登录接口",notes = "用户登录成功后会返回客户端一个token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "identify",value = "手机号",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "credential", value = "密码",dataType="String",paramType = "query")}
    )
    @RequestMapping(value = "/usr/login",method = RequestMethod.GET)
    public Result login(@RequestParam(value = "identify") String identify,@RequestParam(value = "credential") String credential){
        String token=userService.validateUser(identify, credential);
        if(token.equals("")) {
            return Result.failure(ResultCode.USER_LOGIN_ERROR);
        }else{
            return Result.success(token);
        }

    }

    @ApiOperation(value = "修改用户信息")
    @ApiImplicitParam(name = "appUser",value = "用户信息对象",dataType = "AppUser")
    @RequestMapping(value = "/ModifyInfo",method = RequestMethod.POST)
    public Result modifyInfo(@RequestBody AppUser appUser){
        boolean r=userService.modifyUserInfo(appUser);
        if(r){
            return Result.success();
        }else{
            return Result.failure(ResultCode.DATA_IS_WRONG);
        }
    }

    @ApiOperation(value = "修改密码")
    @RequestMapping(value = "/modifyPwd",method = RequestMethod.GET)
    public Result modifyPassword(@RequestParam(value = "identify") String identify, @RequestParam(value = "newPwd") String newPwd){
        boolean r=userService.modifyPassword(identify,newPwd);
        if(r){
            return Result.success();
        }else{
            return Result.failure(ResultCode.DATA_IS_WRONG);
        }

    }

}
