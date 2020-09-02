package com.liu.animal.app.service;

import com.liu.animal.app.entity.PetTaskPhoto;

import java.util.List;

/**
 * @ClassName: PetTaskPhotoService
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/17 15:17
 **/

public interface PetTaskPhotoService {
    List<PetTaskPhoto> findPhoto(String petTaskId, String type);

    int addPhoto(PetTaskPhoto petTaskPhoto);

    int updateById(PetTaskPhoto petTaskPhoto);

    int deleteById(String id);
}