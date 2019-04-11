package com.chrisyoung.appserver.service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.chrisyoung.appserver.dto.VerificationCode;
import com.chrisyoung.appserver.service.IMsgService;
import com.chrisyoung.appserver.utils.AliyunMessageUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-12-03 14:43
 * @description: 短信服务接口实现类
 **/

@Service
public class MsgService implements IMsgService {
    private String randomNum;
    private String jsonContent;
    private Map<String,String> paramMap=new HashMap<>();

    MsgService(){
        randomNum=createRandomNum(6);
        jsonContent="{\"code\":\"" + randomNum + "\"}";
        paramMap.put("msgSign","花匠账本");
        paramMap.put("templateCode","SMS_151233724");
        paramMap.put("jsonContent",jsonContent);

    }
    @Override
    public VerificationCode sendMsg(String phoneNum) {
        paramMap.put("phoneNumber",phoneNum);
        SendSmsResponse sendSmsResponse= null;
        try {
            sendSmsResponse = AliyunMessageUtil.sendSms(paramMap);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if(sendSmsResponse.getCode()!=null && sendSmsResponse.getCode().equals("OK")){
            return new VerificationCode(randomNum);
        }else{
            return new VerificationCode("");
        }
    }

    @Override
    public boolean isCodeExpired(VerificationCode code) {
        return true;
    }

    /**
     * 生成随机数
     * @param num 位数
     * @return
     */
    private String createRandomNum(int num){
        String randomNumStr = "";
        for(int i = 0; i < num;i ++){
            int randomNum = (int)(Math.random() * 10);
            randomNumStr += randomNum;
        }
        return randomNumStr;
    }

}
