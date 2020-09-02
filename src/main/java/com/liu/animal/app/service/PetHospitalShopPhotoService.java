package com.liu.animal.app.service;

import com.liu.animal.app.entity.PetHospitalShopPhoto;

import java.util.List;

/**
 * @ClassName: PetHospitalShopPhoto
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/12 15:53
 **/

public interface PetHospitalShopPhotoService extends ServiceParent<PetHospitalShopPhoto> {
    List<PetHospitalShopPhoto> findByPetShopHospitalId(String id);
}