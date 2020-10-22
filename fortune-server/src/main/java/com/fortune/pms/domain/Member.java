package com.fortune.pms.domain;

import java.sql.Date;

public class Member {
  final static int ADMINISTER = 1;
  final static int NORMAL_USER = 2;

  private String name;
  private String password;
  private String id;
  private int userGrade;
  private Date registeredDate;


  public Member() {

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

  public int getUserGrade() {
    return userGrade;
  }
}
