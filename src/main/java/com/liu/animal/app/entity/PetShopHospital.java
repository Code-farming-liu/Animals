package com.liu.animal.app.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Admin
 * @Description 宠物店和宠物医院的信息
 * @Date 10:26 2020/5/2
 * @return
 **/

public class PetShopHospital implements Serializable {

  private String id;
  private String name;
  private String type;
  private String location;
  private String evaluate;
  private String description;
  private String createUserId;
  private String license;
  private String phone;
  private String qq;
  private String wx;
  private Integer countOfPage;
  private List<PetHospitalShopPhoto> petHospitalShopPhotoList;

  public List<PetHospitalShopPhoto> getPetHospitalShopPhotoList() {
    return petHospitalShopPhotoList;
  }

  public void setPetHospitalShopPhotoList(List<PetHospitalShopPhoto> petHospitalShopPhotoList) {
    this.petHospitalShopPhotoList = petHospitalShopPhotoList;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getEvaluate() {
    return evaluate;
  }

  public void setEvaluate(String evaluate) {
    this.evaluate = evaluate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCreateUserId() {
    return createUserId;
  }

  public void setCreateUserId(String createUserId) {
    this.createUserId = createUserId;
  }

  public String getLicense() {
    return license;
  }

  public void setLicense(String license) {
    this.license = license;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getQq() {
    return qq;
  }

  public void setQq(String qq) {
    this.qq = qq;
  }

  public String getWx() {
    return wx;
  }

  public void setWx(String wx) {
    this.wx = wx;
  }
}
