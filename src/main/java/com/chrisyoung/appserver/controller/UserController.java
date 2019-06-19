package com.chrisyoung.appserver.controller;


import com.chrisyoung.appserver.constant.ResultCode;
import com.chrisyoung.appserver.domain.AppUser;
import com.chrisyoung.appserver.domain.UserAuths;
import com.chrisyoung.appserver.dto.HttpResult;
import com.chrisyoung.appserver.service.IUploadImageService;
import com.chrisyoung.appserver.service.impl.UploadImageServie;
import com.chrisyoung.appserver.service.impl.UserService;
import com.chrisyoung.appserver.utils.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.http.client.methods.HttpHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-11-06 11:32
 * @description: 注册接口
 **/

@Api(value = "用户Controller")
@RestController
@RequestMapping("/usr")
public class UserController {
    private  final UserService userService;
    private final IUploadImageService uploadImageService;
    private final HttpServletRequest request;

    @Autowired
    public UserController(UserService userService, UploadImageServie uploadImageService, HttpServletRequest request) {
        this.userService = userService;
        this.uploadImageService = uploadImageService;
        this.request = request;
    }

    @ApiOperation(value = "注册新用户")
    @ApiImplicitParam(name = "userAuths",value = "用户权限对象",dataType = "UserAuths")
    @RequestMapping(value = "/auth/register",method = RequestMethod.POST)
    public HttpResult register(@RequestBody UserAuths userAuths){
        HttpResult<String> httpResult=new HttpResult<>();
        if(userService.isExist(userAuths.getIdentify(),userAuths.getCredential())){
            httpResult.setResultCode(ResultCode.USER_HAS_EXISTED);
        }else{
            Boolean result=userService.registerUser(userAuths);
            if(result){
                httpResult.setResultCode(ResultCode.SUCCESS);
            }else{
                httpResult.setResultCode(ResultCode.DATA_IS_WRONG);
            }

        }
        return httpResult;
    }

    @ApiOperation(value ="登录接口",notes = "用户登录成功后会返回客户端一个token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "identify",value = "手机号",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "credential", value = "密码",dataType="String",paramType = "query")}
    )
    @RequestMapping(value = "/auth/login",method = RequestMethod.GET)
    public HttpResult login(@RequestParam(value = "identify") String identify, @RequestParam(value = "credential") String credential){
        String token=userService.validateUser(identify, credential);
        HttpResult<String> httpResult=new HttpResult<>();
        if(token.equals("")) {
            httpResult.setResultCode(ResultCode.USER_LOGIN_ERROR);
        }else{
            httpResult.setResultCode(ResultCode.SUCCESS);
        }
        httpResult.setData(token);
        return httpResult;
    }

    @RequestMapping(value = "/getuid",method = RequestMethod.GET)
    public HttpResult getId(@RequestHeader HttpHeaders token){
        String uId=new JWTUtil().getUserIdFromToken(request.getHeader("Authorization"));
        AppUser appUser=userService.showUserInfo(uId);
        HttpResult<AppUser> httpResult=new HttpResult<>();
        if(token.equals("")) {
            httpResult.setResultCode(ResultCode.USER_LOGIN_ERROR);
        }else{
            httpResult.setResultCode(ResultCode.TEST);
        }
        httpResult.setData(appUser);
        return httpResult;

    }

    @ApiOperation(value = "修改用户信息")
    @ApiImplicitParam(name = "appUser",value = "用户信息对象",dataType = "AppUser")
    @RequestMapping(value = "/modifyInfo",method = RequestMethod.POST)
    public HttpResult modifyInfo(@RequestBody AppUser appUser){
        boolean r=userService.modifyUserInfo(appUser);
        HttpResult<String> httpResult=new HttpResult<>();
        if(r){
            httpResult.setResultCode(ResultCode.SUCCESS);
        }else{
           httpResult.setResultCode(ResultCode.DATA_IS_WRONG);
        }
        return httpResult;
    }

    @ApiOperation(value = "修改密码")
    @RequestMapping(value = "/modifyPwd",method = RequestMethod.GET)
    public HttpResult modifyPassword(@RequestParam(value = "identify") String identify, @RequestParam(value = "newPwd") String newPwd){
        boolean r=userService.modifyPassword(identify,newPwd);
        HttpResult<String> httpResult=new HttpResult<>();
        if(r){
            httpResult.setResultCode(ResultCode.SUCCESS);
        }else{
            httpResult.setResultCode(ResultCode.DATA_IS_WRONG);
        }
        return httpResult;

    }

    @ApiOperation(value = "上传图片接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "图片文件",dataType = "MultipartFile",paramType = "body"),
            @ApiImplicitParam(name = "用户ID",dataType = "String",paramType = "header")
    })

    @PostMapping("uploadPhoto")
    public HttpResult uploadHeadPhoto(@RequestParam("image") MultipartFile img, @RequestHeader("UserId") String uId){
        String imgPath=uploadImageService.UploadImage(img,uId);
        HttpResult<String> result=new HttpResult<>();
        if(imgPath.equals("")){
            result.setResultCode(ResultCode.DATA_IS_WRONG);
        }else{
            result.setResultCode(ResultCode.SUCCESS);
        }
        result.setData(imgPath);
        return result;
    }

}
