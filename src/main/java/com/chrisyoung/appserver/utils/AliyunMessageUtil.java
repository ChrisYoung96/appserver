package com.chrisyoung.appserver.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @program: alimsgdemo
 * @author: Chris Young
 * @create: 2018-11-19 08:45
 * @description: sd
 **/

@Component
public class AliyunMessageUtil {
    //短信API产品名称（短信产品名固定，无需修改）
    private static final String product = "Dysmsapi";
    //短信API产品域名（接口地址固定，无需修改）
    private static final String domain = "dysmsapi.aliyuncs.com";
    //替换成你的accessKeyId
    private static final String accessKeyId = "LTAInn2OHJhkkSe9";
    //你的accessKeySecret
    private static final String accessKeySecret = "8E3n4jygkvcJijv17LjEUBaDSFrdoK";


    /**
     *
     * @param paramMap
     * phoneNumber：接受者手机号
     *
     * msgSign：短信签名名称。在控制台的短信签名里能找到。
     *
     * templateCode：短信模版的code。见控制台中的模版code。
     *
     * jsonContent：需要替换的变量的JSON字符串。对于验证码来说，String jsonContent = "{\"number\":\"" + randomNum + "\"}";即可。其中randomNum是随机生成的6位验证码。
     *
     * @return
     * @throws ClientException
     */
    public static SendSmsResponse sendSms(Map<String, String> paramMap) throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("ch-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.setPhoneNumbers(paramMap.get("phoneNumber"));
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(paramMap.get("msgSign"));
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(paramMap.get("templateCode"));
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(paramMap.get("jsonContent"));

        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        return sendSmsResponse;
    }

}
