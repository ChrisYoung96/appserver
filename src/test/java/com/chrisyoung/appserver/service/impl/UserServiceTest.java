package com.chrisyoung.appserver.service.impl;

import com.chrisyoung.appserver.constant.DataTypeCode;
import com.chrisyoung.appserver.constant.IdentityTypeCode;
import com.chrisyoung.appserver.constant.OptCode;
import com.chrisyoung.appserver.domain.AppUser;
import com.chrisyoung.appserver.domain.UserAuths;
import com.chrisyoung.appserver.dto.SychronizeDataItem;
import com.chrisyoung.appserver.dto.SychronizeDataModel;
import org.apache.catalina.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedList;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService service;

    @Test
    public void registerUser() {
//        AppUser appUser=new AppUser();
//        UserAuths userAuths=new UserAuths();
//        userAuths.setUId(appUser.getUId());
//        userAuths.setIdentityType(IdentityTypeCode.PHONE);
//        userAuths.setIdentify("13181891145");
//        userAuths.setCredential("123456");
//        SychronizeDataItem<AppUser> au=new SychronizeDataItem<>();
//        au.setData(appUser);
//        au.setOptCode(OptCode.INSERT);
//        SychronizeDataItem<UserAuths> u=new SychronizeDataItem<>();
//        u.setOptCode(OptCode.INSERT);
//        u.setData(userAuths);
//        SychronizeDataModel<AppUser> am=new SychronizeDataModel<>();
//        am.setDataTypeCode(DataTypeCode.APPUSER);
//        am.getSychQueue().offer(au);
//        SychronizeDataModel<UserAuths> um=new SychronizeDataModel<>();
//        um.setDataTypeCode(DataTypeCode.USERAUTHS);
//        um.getSychQueue().offer(u);
//        LinkedList<SychronizeDataModel> datas=new LinkedList<>();
//        datas.add(am);
//        datas.add(um);
//        if(service.registerUser(datas)){
//            System.out.println("注册成功");
//        }else{
//            System.out.println("注册失败");
//        }
        String uid=UUID.randomUUID().toString().replace("-","");
        String type=IdentityTypeCode.PHONE;
        String phone="13167781234";
        String code="111111";
        Assert.assertEquals(true,service.registerUser(uid,type,phone,code));
    }

    @Test
    public void validateUser() {
    }

    @Test
    public void modifyUserInfo() {
    }

    @Test
    public void showUserInfo() {
    }

    @Test
    public void addNewValidation() {
    }

    @Test
    public void modifyPassword() {
    }
}