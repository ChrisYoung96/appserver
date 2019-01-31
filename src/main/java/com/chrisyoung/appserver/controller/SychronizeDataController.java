package com.chrisyoung.appserver.controller;

import com.chrisyoung.appserver.constant.ResultCode;
import com.chrisyoung.appserver.domain.AppUser;
import com.chrisyoung.appserver.domain.Bill;
import com.chrisyoung.appserver.domain.Record;
import com.chrisyoung.appserver.domain.UserDiy;
import com.chrisyoung.appserver.dto.HttpResult;
import com.chrisyoung.appserver.dto.SychronizeDataItem;
import com.chrisyoung.appserver.service.impl.SychronizeDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedList;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-11-12 20:13
 * @description: 数据同步控制器
 **/
@Api(value = "数据同步Controller")
@RestController
@RequestMapping("/sychdata")
public class SychronizeDataController {
    private final SychronizeDataService service;

    public SychronizeDataController(SychronizeDataService service) {
        this.service = service;
    }

    @ApiOperation(value = "客户端同步服务器端用户信息数据")
    @RequestMapping(value = "/userInofs2c",method = RequestMethod.GET)
    public HttpResult sychronizeUserInfoS2C(@RequestHeader("UserId") String uId){
        AppUser appUser=service.sychronizeUserInfoS2C(uId);
        HttpResult<AppUser> httpResult=new HttpResult<>();
        if(appUser!=null){
            httpResult.setResultCode(ResultCode.SUCCESS);
        }else{
            httpResult.setResultCode(ResultCode.USER_NOT_EXIST);
        }
        httpResult.setData(appUser);
        return httpResult;
    }

    @ApiOperation(value = "客户端同步服务器端账本信息")
    @RequestMapping(value = "/bills2c",method = RequestMethod.GET)
    public HttpResult sychronizeBillS2C(@RequestHeader("UserId") String uId){
        LinkedList<SychronizeDataItem<Bill>> bills=service.sychronizeBillsS2C(uId);
        HttpResult<LinkedList<SychronizeDataItem<Bill>>> httpResult=new HttpResult<>();
        if(bills!=null){
            if (!bills.isEmpty()){
                httpResult.setData(bills);
                httpResult.setResultCode(ResultCode.SUCCESS);
            }else {
                httpResult.setResultCode(ResultCode.DATA_IS_WRONG);
            }
        }else{
            httpResult.setResultCode(ResultCode.USER_NOT_EXIST);
        }
        return httpResult;
    }

    @ApiOperation(value = "客户端同步服务器端记录信息")
    @RequestMapping(value = "/records2c",method = RequestMethod.GET)
    public HttpResult sychronizeRecordsS2C(@RequestParam("bId") String bId){
        LinkedList<SychronizeDataItem<Record>> records=service.sychronizeRecordsS2C(bId);
        HttpResult<LinkedList<SychronizeDataItem<Record>>> httpResult=new HttpResult<>();
        if(records!=null){
            if (!records.isEmpty()){
                httpResult.setData(records);
                httpResult.setResultCode(ResultCode.SUCCESS);
            }else {
                httpResult.setResultCode(ResultCode.PARAM_IS_INVALID);
            }
        }else{
            httpResult.setResultCode(ResultCode.DATA_IS_WRONG);
        }
        return  httpResult;
    }

    @ApiOperation(value = "客户端同步服务器端自定义支出类型信息")
    @RequestMapping(value = "/diykindss2c",method = RequestMethod.GET)
    public HttpResult sychronizeDiyKindsS2C(@RequestHeader("UserId") String uId){
        LinkedList<SychronizeDataItem<UserDiy>> diyKinds=service.sychronizeUserDiyKindS2C(uId);
        HttpResult<LinkedList<SychronizeDataItem<UserDiy>>> httpResult=new HttpResult<>();
        if(diyKinds!=null){
            if (!diyKinds.isEmpty()){
                httpResult.setData(diyKinds);
                httpResult.setResultCode(ResultCode.SUCCESS);
            }else {
                httpResult.setResultCode(ResultCode.DATA_IS_WRONG);
            }
        }else{
            httpResult.setResultCode(ResultCode.USER_NOT_EXIST);
        }
        return httpResult;
    }

    @ApiOperation(value = "服务器端同步客户端账本信息")
    @RequestMapping(value = "/billsc2s", method = RequestMethod.POST)
    public HttpResult sychronizeBillsC2S(@RequestBody LinkedList<SychronizeDataItem<Bill>> bills){
        HttpResult<String> httpResult=new HttpResult<>();
        if (service.sychronizeBillsC2S(bills)){
            httpResult.setResultCode(ResultCode.SUCCESS);
        }else{
            httpResult.setResultCode(ResultCode.DATA_IS_WRONG);
        }
        return httpResult;
    }

    @ApiOperation(value = "服务器端同步客户端记录信息")
    @RequestMapping(value = "/recordsc2s", method = RequestMethod.POST)
    public HttpResult sychronizeRecordsC2S(@RequestBody LinkedList<SychronizeDataItem<Record>> records){
        HttpResult<String> httpResult=new HttpResult<>();
        if (service.sychronizeRecordsC2S(records)){
            httpResult.setResultCode(ResultCode.SUCCESS);
        }else{
            httpResult.setResultCode(ResultCode.DATA_IS_WRONG);
        }
        return httpResult;
    }

    @ApiOperation(value = "服务器端同步客户端自定义支出类型信息")
    @RequestMapping(value = "/diykindsc2s", method = RequestMethod.POST)
    public HttpResult sychronizeDiyKindsC2S(@RequestBody LinkedList<SychronizeDataItem<UserDiy>> diykinds){
        HttpResult<String> httpResult=new HttpResult<>();
        if (service.sychronizeUserDiyKindC2S(diykinds)){
            httpResult.setResultCode(ResultCode.SUCCESS);
        }else{
            httpResult.setResultCode(ResultCode.DATA_IS_WRONG);
        }
        return httpResult;
    }

    @ApiOperation(value = "服务器端同步客户端用户信息")
    @RequestMapping(value = "/userInfoc2s", method = RequestMethod.POST)
    public HttpResult sychronizeUserInfoC2S(@RequestBody AppUser user){
        HttpResult<String> httpResult=new HttpResult<>();
        if(service.sychronizeUserInfoC2S(user)){
            httpResult.setResultCode(ResultCode.SUCCESS);
        }else{
            httpResult.setResultCode(ResultCode.DATA_IS_WRONG);
        }
        return httpResult;
    }




}
