package com.chrisyoung.appserver.controller;

import com.chrisyoung.appserver.constant.ResultCode;
import com.chrisyoung.appserver.dto.Result;
import com.chrisyoung.appserver.dto.VerificationCode;
import com.chrisyoung.appserver.service.impl.MsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-12-03 15:05
 * @description: 短信服务接口
 **/
@Api(value = "获取短信验证码Controller")
@RestController
public class MsgController {
    private final MsgService msgService;

    @Autowired
    public MsgController(MsgService msgService) {
        this.msgService = msgService;
    }


    @ApiOperation(value = "获取验证码",notes = "获取短信验证码")
    @ApiImplicitParam(value = "用户手机号",name = "phone",dataType = "String",paramType = "query")
    @RequestMapping(value = "/usr/getmsg",method = RequestMethod.GET)
    public Result getCode(@RequestParam("phone") String phone){
        VerificationCode code=msgService.sendMsg(phone);
        if(code.getCode().equals("")){
            return Result.failure(ResultCode.DATA_IS_WRONG);
        }else{
            return Result.success(code);
        }
    }

    @ApiOperation(value = "校验验证码时效性")
    @ApiImplicitParam(name = "code",value = "用户填好的验证码",dataType = "VerificationCode",paramType = "body")
    @RequestMapping(value = "/usr/validmsg",method = RequestMethod.POST)
    public Result validateCode(@RequestBody VerificationCode code){
        if(msgService.isCodeExpired(code)){
            return Result.failure(ResultCode.CODE_IS_EXPIRED);
        }else{
            return Result.success(code);
        }
    }

}
