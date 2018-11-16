package com.chrisyoung.appserver.dto;

import com.chrisyoung.appserver.domain.Bill;
import com.chrisyoung.appserver.domain.Record;

import java.util.List;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-11-12 20:33
 * @description: 初次登录从服务器加载数据
 **/


public class SychroNizeBillAndRecords {
    private Bill bill;
    private List<Record> records;

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
