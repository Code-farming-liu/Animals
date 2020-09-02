package com.liu.animal.app.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * @Author Admin
 * @Description 营业执照
 * @Date 10:36 2020/5/2
 * @return
 **/
public class UserBusinessLicense implements Serializable {

  private String id;
  private String workName;
  private String type;
  private String username;
  private String address;
  private Date effectiveTime;
  private String certificateNumber;
  private String businessLicensePhoto;
  private Integer countOfPage;
  private String userInfoId;
  private String isUpdate;
  private String image;

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

  public String getWorkName() {
    return workName;
  }

  public void setWorkName(String workName) {
    this.workName = workName;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Date getEffectiveTime() {
    return effectiveTime;
  }

  public void setEffectiveTime(Date effectiveTime) {
    this.effectiveTime = effectiveTime;
  }

  public String getCertificateNumber() {
    return certificateNumber;
  }

  public void setCertificateNumber(String certificateNumber) {
    this.certificateNumber = certificateNumber;
  }

  public String getBusinessLicensePhoto() {
    return businessLicensePhoto;
  }

  public void setBusinessLicensePhoto(String businessLicensePhoto) {
    this.businessLicensePhoto = businessLicensePhoto;
  }
}
