package com.chrisyoung.appserver.service;

import com.chrisyoung.appserver.domain.UserDiy;

import java.util.ArrayList;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-12-06 11:42
 * @description: 用户自定义收支类型业务逻辑
 **/

public interface IUserDiyKindService {
    ArrayList<UserDiy> showAllDiyKind(String uId);

    boolean addNewDiyKind(UserDiy newKind);

    boolean deleteDiyKind(int dId);
}
