package com.chrisyoung.appserver.service;

import com.chrisyoung.appserver.dto.VerificationCode;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-12-01 10:00
 * @description:
 **/

public interface IMsgService {
    VerificationCode sendMsg(String phoneNum);
    boolean isCodeExpired(VerificationCode code);
}
