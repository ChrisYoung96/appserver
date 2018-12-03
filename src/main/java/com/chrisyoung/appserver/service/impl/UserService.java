package com.chrisyoung.appserver.service.impl;

import com.chrisyoung.appserver.dao.AppUserDao;
import com.chrisyoung.appserver.dao.UserAuthsDao;
import com.chrisyoung.appserver.domain.AppUser;
import com.chrisyoung.appserver.domain.UserAuths;
import com.chrisyoung.appserver.service.IUserService;
import com.chrisyoung.appserver.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private JWTUtil jwtUtil;


    @Autowired
    public UserService(AppUserDao appUserDao, UserAuthsDao userAuthsDao,JWTUtil jwtUtil) {
        this.appUserDao = appUserDao;
        this.userAuthsDao = userAuthsDao;
        this.jwtUtil=jwtUtil;
    }


    @Override
    public boolean registerUser(UserAuths newAuth) {
        int result=0;
        AppUser newUser=new AppUser();
        newUser.setUId(newAuth.getUId());
        result+=appUserDao.addUser(newUser);
        result+=userAuthsDao.addAuth(newAuth);
        return result==2;
    }

    @Override
    public String validateUser(String identify, String credentail) {
        UserAuths auths=userAuthsDao.findAuth(identify,credentail);
        if(auths==null){
            return "";
        }
        String token=jwtUtil.generateToken(auths.getUId(),auths.getRole());
        return token;
    }

 

    @Override
    public boolean modifyUserInfo(AppUser user) {
        int result;
        result=appUserDao.updateUserInfo(user);
        return result!=0;
    }

    @Override
    public AppUser showUserInfo(String uId) {
        AppUser userInfo=appUserDao.findUserById(uId);
        return userInfo;
    }

    @Override
    public boolean addNewValidation(UserAuths newAuth) {
        int result;
        result=userAuthsDao.addAuth(newAuth);
        return result!=0;
    }

    @Override
    public boolean modifyPassword(String identify,String newPwd) {
        int result=0;
        result=userAuthsDao.updateAuth(identify,newPwd);
        return result!=0;
    }
}
