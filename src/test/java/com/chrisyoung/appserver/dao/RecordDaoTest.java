package com.chrisyoung.appserver.dao;

import com.chrisyoung.appserver.domain.Record;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RecordDaoTest {

    @Autowired
    private RecordDao dao;

    @Test
    public void addRecord() {
        Record r=new Record();
        r.setBId("f576b3ca86c545d69cc15fa4f9c235b4");
        r.setRType("支出");
        r.setRKind("吃饭");
        r.setRMoney(BigDecimal.valueOf(17.00));
        r.setRTime(Timestamp.valueOf("2018-11-10 0:0:0"));
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
        List<Record> records=dao.findAllRecord("9e4b5a7a-a5a9-4438-bb9a-86f23a4264ad");
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
        Timestamp s=Timestamp.valueOf("2018-11-1 0:0:0");
        Timestamp e=Timestamp.valueOf("2018-11-10 0:0:0");
        List<Record> rs=dao.findRecordsBetweenTime("9e4b5a7a-a5a9-4438-bb9a-86f23a4264ad",s,e);
        for (Record r:
             rs) {
            System.out.println(r.getRType()+" "+r.getRKind()+" "+r.getRMoney()+" "+r.getRTime().toString());

        }
    }
}