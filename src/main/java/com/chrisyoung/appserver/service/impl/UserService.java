package com.chrisyoung.appserver.service.impl;

import com.chrisyoung.appserver.constant.DataTypeCode;
import com.chrisyoung.appserver.dao.AppUserDao;
import com.chrisyoung.appserver.dao.UserAuthsDao;
import com.chrisyoung.appserver.domain.AppUser;
import com.chrisyoung.appserver.domain.UserAuths;
import com.chrisyoung.appserver.dto.SychronizeDataItem;
import com.chrisyoung.appserver.dto.SychronizeDataModel;
import com.chrisyoung.appserver.service.IAppUserService;
import com.chrisyoung.appserver.service.IUserAuthsService;
import com.chrisyoung.appserver.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-11-16 09:57
 * @description: 用户服务类
 **/

@Service
public class UserService implements IUserService {
    private final AppUserDao appUserDao;

    private final UserAuthsDao userAuthsDao;

    @Autowired
    public UserService(AppUserDao appUserDao, UserAuthsDao userAuthsDao) {
        this.appUserDao = appUserDao;
        this.userAuthsDao = userAuthsDao;
    }


    @Override
    public boolean registerUser(String uId,String identifyType,String identify,String credential) {
        int result=0;
        AppUser newUser=new AppUser();
        newUser.setUId(uId);
        UserAuths newAuth=new UserAuths();
        newAuth.setUId(uId);
        newAuth.setIdentityType(identifyType);
        newAuth.setIdentify(identify);
        newAuth.setCredential(credential);
        result=appUserDao.addUser(newUser);
        result=userAuthsDao.addAuth(newAuth);
        return result!=0;
    }

    @Override
    public boolean validateUser(String identify, String credentail) {
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
    public boolean addNewValidation(UserAuths newAuth) {
        return false;
    }

    @Override
    public boolean modifyPassword(String uId, String newPwd) {
        return false;
    }
}
