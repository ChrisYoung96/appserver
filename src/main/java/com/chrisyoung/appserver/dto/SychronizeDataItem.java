package com.chrisyoung.appserver.dto;

/**
 * @program: appserver
 * @author: Chris Young
 * @create: 2018-11-06 11:34
 * @description: 数据传输实体类
 **/


public class SychronizeDataItem<T> {
    private T data;
    private int optCode;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getOptCode() {
        return optCode;
    }

    public void setOptCode(int optCode) {
        this.optCode = optCode;
    }
}
