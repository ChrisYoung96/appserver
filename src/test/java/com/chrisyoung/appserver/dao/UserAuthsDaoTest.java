package com.chrisyoung.appserver.dao;

import com.chrisyoung.appserver.constant.IdentityTypeCode;
import com.chrisyoung.appserver.domain.UserAuths;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserAuthsDaoTest {

    @Autowired
    private UserAuthsDao dao;

    @Test
    public void addAuth() {
        UserAuths auths=new UserAuths();
        auths.setUId("3d7a33f500ab44c089281b39783072fb");
        auths.setIdentityType(IdentityTypeCode.PHONE);
        auths.setIdentify("123456");
        auths.setCredential("111111");
        Assert.assertEquals(1,dao.addAuth(auths));

    }

    @Test
    public void findAuthById() {
       int a=dao.findAuth("123456","111111");
        System.out.println(a);


    }

    @Test
    public void updateAuth() {
        UserAuths auths=dao.findAuthByuId("3d7a33f500ab44c089281b39783072fb","123456");
        auths.setCredential("12345888");
        Assert.assertEquals(1,dao.updateAuth(auths));
    }
}