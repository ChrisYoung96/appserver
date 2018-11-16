package com.chrisyoung.appserver.dao;

import com.chrisyoung.appserver.domain.Bill;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BillDaoTest {
    @Autowired
    private BillDao dao;

    @Test
    public void addBill() {
        Bill bill=new Bill();
        bill.setBName("生活");
        bill.setUId("3d7a33f500ab44c089281b39783072fb");
        bill.setBDesc("");
        Assert.assertEquals(1,dao.addBill(bill));
    }

    @Test
    public void findAllBills() {
        List<Bill> bills=dao.findAllBills("3d7a33f500ab44c089281b39783072fb");
        for (Bill b :
                bills) {
            System.out.println(b.getBName());

        }
    }

    @Test
    public void updateBill() {
        Bill bill=dao.findBillById("4a66f148-b367-4c83-96a9-87b79b01f598");
        System.out.println(bill.getBName());
        bill.setBDesc("haha");
        Assert.assertEquals(1,dao.updateBill(bill));
    }

    @Test
    public void deleteBill() {
        Assert.assertEquals(1,dao.deleteBill("f576b3ca86c545d69cc15fa4f9c235b4"));
    }
}