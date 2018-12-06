package com.chrisyoung.appserver.dao;

import com.chrisyoung.appserver.domain.Record;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RecordDaoTest {

    @Autowired
    private RecordDao dao;

    @Test
    public void addRecord() {
        Record r=new Record();
        r.setBId("12345678901");
        r.setRType("支出");
        r.setRKind("购物");
        r.setRMoney(BigDecimal.valueOf(17.00));
        r.setrWay("支付宝");
        r.setRTime(new Timestamp(System.currentTimeMillis()));
        r.setRDesc("测试");
        Assert.assertEquals(1,dao.addRecord(r));
    }

    @Test
    public void updateRecord() {
        Record r=dao.findRecordById("60ea3021af7a4836bc03752ad637b0aa");
        r.setRMoney(BigDecimal.valueOf(12.44));
        Assert.assertEquals(1,dao.updateRecord(r));
    }

    @Test
    public void findAllRecord() {
        List<Record> records=dao.findAllRecord("12345678901");
        for (Record r :
                records) {
            System.out.println(r.getRType()+" "+r.getRKind()+" "+r.getRMoney()+" "+r.getRTime().toString());
        }
    }

    @Test
    public void deleteRecord() {
        Assert.assertEquals(1,dao.deleteRecord("53e94985463c4d71ba2df7fb441d502e"));
    }

    @Test public void findRecordsBetweenTime(){
        Time s=Time.valueOf("2018-11-1 0:0:0");
        Time e=Time.valueOf("2018-11-10 0:0:0");
        List<Record> rs=dao.findRecordsBetweenTime("9e4b5a7a-a5a9-4438-bb9a-86f23a4264ad",s,e);
        for (Record r:
             rs) {
            System.out.println(r.getRType()+" "+r.getRKind()+" "+r.getRMoney()+" "+r.getRTime().toString());

        }
    }
}