package com.chrisyoung.appserver.service;

import com.chrisyoung.appserver.domain.AppUser;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-11-06 11:35
 * @description: 用户服务接口
 **/

public interface IAppUserService {
    boolean addNewUserInfo(AppUser newUser);

    boolean modifyUserInfo(AppUser user);

    AppUser showUserInfo(String uId);
}
