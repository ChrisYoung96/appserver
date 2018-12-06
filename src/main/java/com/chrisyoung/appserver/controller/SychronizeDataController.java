package com.chrisyoung.appserver.controller;

import com.chrisyoung.appserver.constant.ResultCode;
import com.chrisyoung.appserver.domain.AppUser;
import com.chrisyoung.appserver.domain.Bill;
import com.chrisyoung.appserver.domain.Record;
import com.chrisyoung.appserver.domain.UserDiy;
import com.chrisyoung.appserver.dto.Result;
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
    public Result sychronizeUserInfoS2C(@RequestHeader("UserId") String uId){
        AppUser appUser=service.sychronizeUserInfoS2C(uId);
        if(appUser!=null){
            return Result.success(appUser);
        }else{
            return Result.failure(ResultCode.USER_NOT_EXIST);
        }
    }

    @ApiOperation(value = "客户端同步服务器端账本信息")
    @RequestMapping(value = "/bills2c",method = RequestMethod.GET)
    public Result sychronizeBillS2C(@RequestHeader("UserId") String uId){
        LinkedList<SychronizeDataItem<Bill>> bills=service.sychronizeBillsS2C(uId);
        if(bills!=null){
            if (!bills.isEmpty()){
                return Result.success(bills);
            }else {
                return Result.failure(ResultCode.DATA_IS_WRONG);
            }
        }else{
            return Result.failure(ResultCode.USER_NOT_EXIST);
        }
    }

    @ApiOperation(value = "客户端同步服务器端记录信息")
    @RequestMapping(value = "/records2c",method = RequestMethod.GET)
    public Result sychronizeRecordsS2C(@RequestParam("bId") String bId){
        LinkedList<SychronizeDataItem<Record>> records=service.sychronizeRecordsS2C(bId);
        if(records!=null){
            if (!records.isEmpty()){
                return Result.success(records);
            }else {
                return Result.failure(ResultCode.PARAM_IS_INVALID);
            }
        }else{
            return Result.failure(ResultCode.DATA_IS_WRONG);
        }
    }

    @ApiOperation(value = "客户端同步服务器端自定义支出类型信息")
    @RequestMapping(value = "/diykindss2c",method = RequestMethod.GET)
    public Result sychronizeDiyKindsS2C(@RequestHeader("UserId") String uId){
        LinkedList<SychronizeDataItem<UserDiy>> diyKinds=service.sychronizeUserDiyKindS2C(uId);
        if(diyKinds!=null){
            if (!diyKinds.isEmpty()){
                return Result.success(diyKinds);
            }else {
                return Result.failure(ResultCode.DATA_IS_WRONG);
            }
        }else{
            return Result.failure(ResultCode.USER_NOT_EXIST);
        }
    }

    @ApiOperation(value = "服务器端同步客户端账本信息")
    @RequestMapping(value = "/billsc2s", method = RequestMethod.POST)
    public Result sychronizeBillsC2S(@RequestBody LinkedList<SychronizeDataItem<Bill>> bills){
        if (service.sychronizeBillsC2S(bills)){
            return Result.success();
        }else{
            return Result.failure(ResultCode.DATA_IS_WRONG);
        }
    }

    @ApiOperation(value = "服务器端同步客户端记录信息")
    @RequestMapping(value = "/recordsc2s", method = RequestMethod.POST)
    public Result sychronizeRecordsC2S(@RequestBody LinkedList<SychronizeDataItem<Record>> records){
        if (service.sychronizeRecordsC2S(records)){
            return Result.success();
        }else{
            return Result.failure(ResultCode.DATA_IS_WRONG);
        }
    }

    @ApiOperation(value = "服务器端同步客户端自定义支出类型信息")
    @RequestMapping(value = "/diykindsc2s", method = RequestMethod.POST)
    public Result sychronizeDiyKindsC2S(@RequestBody LinkedList<SychronizeDataItem<UserDiy>> diykinds){
        if (service.sychronizeUserDiyKindC2S(diykinds)){
            return Result.success();
        }else{
            return Result.failure(ResultCode.DATA_IS_WRONG);
        }
    }

}
