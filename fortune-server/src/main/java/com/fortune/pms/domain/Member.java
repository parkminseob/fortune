package com.fortune.pms.domain;

import java.sql.Date;

public class Member {
  final static int ADMINISTER = 1;
  final static int NORMAL_USER = 2;

  private String name;
  private String password;
  private String id;
  private String gender;
  private int age;

  private int userGrade;
  private String checkBadMember;
  private Date registeredDate;


  public Member() {

  }

  public Member(String id, String password, String name) {
    this.id = id;
    this.password = password;
    this.name = name;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getCheckBadMember() {
    return checkBadMember;
  }

  public void setCheckBadMember(String checkBadMember) {
    this.checkBadMember = checkBadMember;
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
