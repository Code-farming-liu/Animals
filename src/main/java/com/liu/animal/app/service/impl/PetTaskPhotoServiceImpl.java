package com.liu.animal.app.service.impl;

import com.liu.animal.app.entity.PetTaskPhoto;
import com.liu.animal.app.mapper.PetTaskPhotoMapper;
import com.liu.animal.app.service.PetTaskPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: PetTaskPhotoServiceImpl
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/17 15:27
 **/
@Service
@Slf4j
@Transactional
public class PetTaskPhotoServiceImpl implements PetTaskPhotoService {
    @Autowired
    private PetTaskPhotoMapper petTaskPhotoMapper;

    @Override
    public List<PetTaskPhoto> findPhoto(String petTaskId, String type) {
        List<PetTaskPhoto> petTaskPhotos = null;
        try {
            petTaskPhotos = petTaskPhotoMapper.selectByPetTaskId(petTaskId, type);
        } catch (Exception e) {
            log.error("PetTaskPhotoServiceImpl的findPhoto方法出错" + e.getMessage());
            return null;
        }
        return petTaskPhotos;
    }

    @Override
    public int addPhoto(PetTaskPhoto petTaskPhoto) {
        try {
            return petTaskPhotoMapper.insertInfo(petTaskPhoto);
        } catch (Exception e) {
            log.error("PetTaskPhotoServiceImpl的addPhoto方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateById(PetTaskPhoto petTaskPhoto) {
        try {
            return petTaskPhotoMapper.updateById(petTaskPhoto);
        } catch (Exception e) {
            log.error("PetTaskPhotoServiceImpl的updateById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteById(String id) {
        try {
            return petTaskPhotoMapper.deleteById(id);
        } catch (Exception e) {
            log.error("PetTaskPhotoServiceImpl的deleteById方法出错" + e.getMessage());
            return 0;
        }
    }
}