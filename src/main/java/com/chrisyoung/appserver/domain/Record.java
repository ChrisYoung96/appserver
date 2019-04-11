package com.chrisyoung.appserver.domain;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.UUID;

public class Record implements Serializable {

  private String rId; //订单编号
  private String bId; //账本编号
  private String rType; //收入或支出
  private String rKind; //收入或支出的类型（支持自定义）
  private BigDecimal rMoney; //金额
  private String rWay; //支付方式
  private Timestamp rTime; //交易时间
  private String rDesc; //备注
  private int rVersion;//记录版本


  public Record(){
    this.rId=UUID.randomUUID().toString().replace("-","");
    this.bId="";
    this.rType="";
    this.rKind="";
    this.rMoney=BigDecimal.valueOf(0,2);
    this.rWay="";
    this.rTime=Timestamp.valueOf("2000-01-01 00:00:00");
    this.rDesc="";
  }

  public String getrId() {
    return rId;
  }

  public void setrId(String rId) {
    this.rId = rId;
  }


  public String getbId() {
    return bId;
  }

  public void setbId(String bId) {
    this.bId = bId;
  }


  public String getrType() {
    return rType;
  }

  public void setrType(String rType) {
    this.rType = rType;
  }


  public String getrKind() {
    return rKind;
  }

  public void setrKind(String rKind) {
    this.rKind = rKind;
  }


  public BigDecimal getrMoney() {
    return rMoney;
  }

  public void setrMoney(BigDecimal rMoney) {
    this.rMoney = rMoney;
  }

  public String getrWay() {
    return rWay;
  }

  public void setrWay(String rWay) {
    this.rWay = rWay;
  }

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public java.sql.Timestamp getrTime() {
    return rTime;
  }

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public void setrTime(java.sql.Timestamp rTime) {
    this.rTime = rTime;
  }


  public String getRDesc() {
    return rDesc;
  }

  public void setrDesc(String rDesc) {
    this.rDesc = rDesc;
  }

  public int getrVersion() {
    return rVersion;
  }

  public void setrVersion(int rVersion) {
    this.rVersion = rVersion;
  }
}
