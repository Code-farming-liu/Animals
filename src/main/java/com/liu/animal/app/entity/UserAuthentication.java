package com.liu.animal.app.entity;


import java.io.Serializable;
import java.util.Date;
/**
 * @Author Admin
 * @Description 用户实名认证表
 * @Date 10:35 2020/5/2
 * @return
 **/

public class UserAuthentication implements Serializable {

  private String id;
  private String username;
  private String idNumber;
  private String bankCardNumber;
  private String address;
  private String sex;
  private Date birthday;
  private String nation;
  private String phone;
  private String idCardPhoto;
  private Integer countOfPage;
  private String userInfoId;
  private String isUpdate;
  private String image;
  private String liveImage1;
  private String idCardImage2;
  private String email;

  public String getLiveImage1() {
    return liveImage1;
  }

  public void setLiveImage1(String liveImage1) {
    this.liveImage1 = liveImage1;
  }

  public String getIdCardImage2() {
    return idCardImage2;
  }

  public void setIdCardImage2(String idCardImage2) {
    this.idCardImage2 = idCardImage2;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getIsUpdate() {
    return isUpdate;
  }

  public void setIsUpdate(String isUpdate) {
    this.isUpdate = isUpdate;
  }

  public String getUserInfoId() {
    return userInfoId;
  }

  public void setUserInfoId(String userInfoId) {
    this.userInfoId = userInfoId;
  }

  public Integer getCountOfPage() {
    return countOfPage;
  }

  public void setCountOfPage(Integer countOfPage) {
    this.countOfPage = countOfPage;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getIdNumber() {
    return idNumber;
  }

  public void setIdNumber(String idNumber) {
    this.idNumber = idNumber;
  }

  public String getBankCardNumber() {
    return bankCardNumber;
  }

  public void setBankCardNumber(String bankCardNumber) {
    this.bankCardNumber = bankCardNumber;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getNation() {
    return nation;
  }

  public void setNation(String nation) {
    this.nation = nation;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getIdCardPhoto() {
    return idCardPhoto;
  }

  public void setIdCardPhoto(String idCardPhoto) {
    this.idCardPhoto = idCardPhoto;
  }
}
