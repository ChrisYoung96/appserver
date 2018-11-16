package com.chrisyoung.appserver.dto;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-11-13 21:19
 * @description: 同步数据模型
 **/


public class SychronizeDataModel<T> {
    private int dataTypeCode; //同步数据类型：1.AppUser,2.UserAuths,3.Bill,4.Record
    private Queue<SychronizeDataItem<T>> sychQueue; //待同步的数据队列

    public SychronizeDataModel() {
        sychQueue= new LinkedList<>();
    }

    public int getDataTypeCode() {
        return dataTypeCode;
    }

    public void setDataTypeCode(int dataTypeCode) {
        this.dataTypeCode = dataTypeCode;
    }

    public Queue<SychronizeDataItem<T>> getSychQueue() {
        return sychQueue;
    }

    public void setSychQueue(Queue<SychronizeDataItem<T>> sychQueue) {
        this.sychQueue = sychQueue;
    }
}
