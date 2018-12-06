package com.chrisyoung.appserver.service.impl;

import com.chrisyoung.appserver.dao.UserDiyDao;
import com.chrisyoung.appserver.domain.UserDiy;
import com.chrisyoung.appserver.service.IUserDiyKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-12-06 11:44
 * @description: 用户自定义收支类型业务逻辑实现类
 **/

@Service
public class UserDiyKindService implements IUserDiyKindService {
    private final UserDiyDao userDiyDao;

    @Autowired
    public UserDiyKindService(UserDiyDao userDiyDao) {
        this.userDiyDao = userDiyDao;
    }

    @Override
    public ArrayList<UserDiy> showAllDiyKind(String uId) {
        ArrayList<UserDiy> userDiys=userDiyDao.findAllDiyKind(uId);
        if(!userDiys.isEmpty()){
            return userDiys;
        }else{
            return null;
        }
    }

    @Override
    public boolean addNewDiyKind(UserDiy newKind) {
        int result=0;
        result=userDiyDao.addNewKind(newKind);
        return result==1;
    }

    @Override
    public boolean deleteDiyKind(int  dId) {
        int result=userDiyDao.deleteKind(dId);
        return result==1;
    }
}
