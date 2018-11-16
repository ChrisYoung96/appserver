package com.chrisyoung.appserver.service;

import com.chrisyoung.appserver.domain.UserAuths;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-11-06 11:36
 * @description: 用户登陆授权服务接口
 **/

public interface IUserAuthsService {
    boolean validateUser(String identify,String credentail);

    boolean addNewValidation(UserAuths newAuth);

    boolean modifyPassword(String uId,String newPwd);


}
