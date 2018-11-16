package com.chrisyoung.appserver.dao;

import com.chrisyoung.appserver.domain.AppUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AppUserDaoTest {

    @Autowired
    private AppUserDao dao;

    @Test
    public void addUser() {
        AppUser user=new AppUser();
        user.setUId(UUID.randomUUID().toString().replace("-",""));
        user.setUName("刘暗松");
        user.setUSex("男");
        user.setUBirthday(Date.valueOf("1999-1-1"));
        user.setUPhone("13181899122");
        user.setUMail("1234d@345.com");
        user.setUPhoto("");
        Assert.assertEquals(1,dao.addUser(user));

    }

    @Test
    public void findUserById() {
        AppUser u=dao.findUserById("4a0dd0da-2e1e-4296-89f0-e116a4467f22");
        System.out.println(u.getUName());

    }

    @Test
    public void updateUserInfo() {
        AppUser user=new AppUser();
        user.setUId("1f066ac71cbc4384a0b78f4042d3f0af");
        user.setUName("刘松");
        user.setUSex("男");
        user.setUBirthday(Date.valueOf("1999-1-1"));
        user.setUPhone("13181899122");
        user.setUMail("1234d@345.com");
        user.setUPhoto("");
        Assert.assertEquals(1,dao.updateUserInfo(user));
    }

    @Test
    public void deleteUser() {
        Assert.assertEquals(1,dao.deleteUser("1f066ac71cbc4384a0b78f4042d3f0af"));
    }
}