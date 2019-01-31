package com.chrisyoung.appserver.domain;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import java.util.UUID;

/**
 * 账本实体
 */
public class Bill {

  private String bId; //账本ID UUID
  private String uId; //用户ID
  private String bName; //账本名称
  private Date bDate; //创建日期
  private String bDesc; //备注
  private int bVersion;//记录版本

  public Bill(){
    this.bId=UUID.randomUUID().toString().replace("-","");
    this.uId="";
    this.bName="";
    this.bDesc="";
  }


  public String getBId() {
    return bId;
  }

  public void setBId(String bId) {
    this.bId = bId;
  }


  public String getUId() {
    return uId;
  }

  public void setUId(String uId) {
    this.uId = uId;
  }


  public String getBName() {
    return bName;
  }

  public void setBName(String bName) {
    this.bName = bName;
  }


  public String getBDesc() {
    return bDesc;
  }

  public void setBDesc(String bDesc) {
    this.bDesc = bDesc;
  }

  public Date getbDate() {
    return bDate;
  }

  public void setbDate(Date bDate) {
    this.bDate = bDate;
  }

  public int getbVersion() {
    return bVersion;
  }

  public void setbVersion(int bVersion) {
    this.bVersion = bVersion;
  }
}
