package com.fortune.pms.domain;

import java.sql.Date;

public class Member {
  private String name;
  private String password;
  private String id;
  private Date registeredDate;

  public Member() {
    this.name = "관리자";
    this.password = "1234";
    this.id = "admin";
  }

  public Member(String id, String password, String name) {
    this.id = id;
    this.password = password;
    this.name = name;
  }

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
}
