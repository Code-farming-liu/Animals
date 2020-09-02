package com.liu.animal.app.entity;

import java.io.Serializable;

/**
 * @ClassName: PetHospitalShopPhoto
 * @Description: 宠物股医院、宠物店的照片
 * @Author: Admin
 * @Date 2020/5/12 15:51
 **/

public class PetHospitalShopPhoto implements Serializable {
    private String id;
    private String photo;
    private String petShopHospitalId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPetShopHospitalId() {
        return petShopHospitalId;
    }

    public void setPetShopHospitalId(String petShopHospitalId) {
        this.petShopHospitalId = petShopHospitalId;
    }
}