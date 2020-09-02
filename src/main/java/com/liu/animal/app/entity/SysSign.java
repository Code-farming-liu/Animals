package com.liu.animal.app.entity;

import java.io.Serializable;

/**
 * @Author Admin
 * @Description 签到表
 * @Date 10:34 2020/5/2
 * @return
 **/

public class SysSign implements Serializable {

  private String id;
  private String userId;
  private String countSign;
  private String mark;
  private String monday;
  private String tuesday;
  private String wednesday;
  private String thursday;
  private String friday;
  private String saturday;
  private String sunday;
  private String todayWeek;
  private String yesterday;

  public String getYesterday() {
    return yesterday;
  }

  public void setYesterday(String yesterday) {
    this.yesterday = yesterday;
  }

  public String getTodayWeek() {
    return todayWeek;
  }

  public void setTodayWeek(String todayWeek) {
    this.todayWeek = todayWeek;
  }




  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getCountSign() {
    return countSign;
  }

  public void setCountSign(String countSign) {
    this.countSign = countSign;
  }

  public String getMark() {

    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }

  public String getMonday() {
    return monday;
  }

  public void setMonday(String monday) {
    this.monday = monday;
  }

  public String getTuesday() {
    return tuesday;
  }

  public void setTuesday(String tuesday) {
    this.tuesday = tuesday;
  }

  public String getWednesday() {
    return wednesday;
  }

  public void setWednesday(String wednesday) {
    this.wednesday = wednesday;
  }

  public String getThursday() {
    return thursday;
  }

  public void setThursday(String thursday) {
    this.thursday = thursday;
  }

  public String getFriday() {
    return friday;
  }

  public void setFriday(String friday) {
    this.friday = friday;
  }

  public String getSaturday() {
    return saturday;
  }

  public void setSaturday(String saturday) {
    this.saturday = saturday;
  }

  public String getSunday() {
    return sunday;
  }

  public void setSunday(String sunday) {
    this.sunday = sunday;
  }

}
