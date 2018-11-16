package com.chrisyoung.appserver.service.impl;

import com.chrisyoung.appserver.domain.AppUser;
import com.chrisyoung.appserver.domain.UserAuths;
import com.chrisyoung.appserver.service.IAppUserService;
import com.chrisyoung.appserver.service.IUserAuthsService;
import org.springframework.stereotype.Service;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-11-16 09:57
 * @description: 用户服务类
 **/

@Service
public class UserService implements IUserAuthsService,IAppUserService {
    @Override
    public boolean addNewUserInfo(AppUser newUser) {
        return false;
    }

    @Override
    public boolean modifyUserInfo(AppUser user) {
        return false;
    }

    @Override
    public AppUser showUserInfo(String uId) {
        return null;
    }

    @Override
    public boolean validateUser(String identify, String credentail) {
        return false;
    }

    @Override
    public boolean addNewValidation(UserAuths newAuth) {
        return false;
    }

    @Override
    public boolean modifyPassword(String uId, String newPwd) {
        return false;
    }
}
