package com.chrisyoung.appserver.dto;

import com.chrisyoung.appserver.domain.Bill;
import org.junit.Assert;
import org.junit.Test;

public class SychronizeDataModelTest {
    SychronizeDataModel<Bill> datas=new SychronizeDataModel<>();

    @Test
    public void test(){
        datas.setDataTypeCode(1);
        Bill b=new Bill();
        SychronizeDataItem<Bill> s=new SychronizeDataItem<>();
        s.setOptCode(1);
        s.setData(b);

        datas.getSychQueue().add(s);
        SychronizeDataItem<Bill> t=datas.getSychQueue().poll();
        System.out.println(t.getData().getBId());
        Assert.assertEquals(true,datas.getSychQueue().isEmpty());

    }

}