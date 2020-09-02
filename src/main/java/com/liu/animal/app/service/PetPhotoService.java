package com.liu.animal.app.service;

import com.liu.animal.app.entity.PetPhoto;

import java.util.List;

public interface PetPhotoService extends ServiceParent<PetPhoto>{
    List<PetPhoto> findByPetInfoId(String id);
}
