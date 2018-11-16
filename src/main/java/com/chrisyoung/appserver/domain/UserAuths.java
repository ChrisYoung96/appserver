package com.chrisyoung.appserver.domain;


public class UserAuths {

  private Long uaId;
  private String uId;  //用户ID
  private String identityType;  //登陆类型
  private String identify;  //电话、邮箱、第三方ID
  private String credential; //密码、第三方登陆为access_token&refresh_token
  private String refreshtoken;


  public Long getUaId() {
    return uaId;
  }

  public void setUaId(Long uaId) {
    this.uaId = uaId;
  }

  public String getUId() {
    return uId;
  }

  public void setUId(String uId) {
    this.uId = uId;
  }


  public String getIdentityType() {
    return identityType;
  }

  public void setIdentityType(String identityType) {
    this.identityType = identityType;
  }


  public String getIdentify() {
    return identify;
  }

  public void setIdentify(String identify) {
    this.identify = identify;
  }


  public String getCredential() {
    return credential;
  }

  public void setCredential(String credential) {
    this.credential = credential;
  }

  public String getRefreshtoken() {
    return refreshtoken;
  }

  public void setRefreshtoken(String refreshtoken) {
    this.refreshtoken = refreshtoken;
  }

}
