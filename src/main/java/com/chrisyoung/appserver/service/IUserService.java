package com.chrisyoung.appserver.service;

import com.chrisyoung.appserver.domain.AppUser;
import com.chrisyoung.appserver.domain.UserAuths;
import com.chrisyoung.appserver.dto.SychronizeDataModel;

import java.util.LinkedList;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-11-16 14:49
 * @description: 用户服务接口
 **/

public interface IUserService {
    boolean registerUser(UserAuths newAuth);

    String validateUser(String identify,String credentail);

    boolean modifyUserInfo(AppUser user);

    AppUser showUserInfo(String uId);

    boolean addNewValidation(UserAuths newAuth);

    boolean modifyPassword(String uId,String identify,String newPwd);
}
