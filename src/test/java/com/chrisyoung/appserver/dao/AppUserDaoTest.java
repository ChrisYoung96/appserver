package com.chrisyoung.appserver.dao;

import com.chrisyoung.appserver.domain.AppUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AppUserDaoTest {

    @Autowired
    private AppUserDao dao;

    @Test
    public void addUser() {
        AppUser user=new AppUser();
        user.setuId(UUID.randomUUID().toString().replace("-",""));
        user.setuName("刘暗松");
        user.setuSex("男");
        user.setuBirthday(Date.valueOf("1999-1-1"));
        user.setuPhone("13181899122");
        user.setuMail("1234d@345.com");
        user.setuPhoto("");
        Assert.assertEquals(1,dao.addUser(user));

    }

    @Test
    public void findUserById() {
        AppUser u=dao.findUserById("4a0dd0da-2e1e-4296-89f0-e116a4467f22");
        System.out.println(u.getuName());

    }

    @Test
    public void updateUserInfo() {
        AppUser user=new AppUser();
        user.setuId("1f066ac71cbc4384a0b78f4042d3f0af");
        user.setuName("刘松");
        user.setuSex("男");
        user.setuBirthday(Date.valueOf("1999-1-1"));
        user.setuPhone("13181899122");
        user.setuMail("1234d@345.com");
        user.setuPhoto("");
        Assert.assertEquals(1,dao.updateUserInfo(user));
    }

    @Test
    public void deleteUser() {
        Assert.assertEquals(1,dao.deleteUser("1f066ac71cbc4384a0b78f4042d3f0af"));
    }
}