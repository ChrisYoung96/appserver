package com.chrisyoung.appserver.controller;

import com.chrisyoung.appserver.constant.ResultCode;
import com.chrisyoung.appserver.dto.Result;
import com.chrisyoung.appserver.service.impl.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-12-03 15:05
 * @description: 短信服务接口
 **/

@RestController
public class MsgController {
    private final MsgService msgService;

    @Autowired
    public MsgController(MsgService msgService) {
        this.msgService = msgService;
    }


    @RequestMapping(value = "/usr/getmsg",method = RequestMethod.GET)
    public Result getCode(@RequestParam("phone") String phone){
        String code=msgService.sendMsg(phone);
        if(code.equals("")){
            return Result.failure(ResultCode.DATA_IS_WRONG);
        }else{
            return Result.success(code);
        }
    }
}
