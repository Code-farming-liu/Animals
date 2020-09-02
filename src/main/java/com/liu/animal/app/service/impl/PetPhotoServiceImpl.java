package com.liu.animal.app.service.impl;

import com.liu.animal.app.entity.PetPhoto;
import com.liu.animal.app.mapper.PetPhotoMapper;
import com.liu.animal.app.service.PetPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: PetPhotoServiceImpl
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/13 10:23
 **/
@Service
@Slf4j
@Transactional
public class PetPhotoServiceImpl implements PetPhotoService {
    @Autowired
    private PetPhotoMapper petPhotoMapper;

    @Override
    public List<PetPhoto> findAll(Integer page, Integer num) {
        List<PetPhoto> petPhotos = null;
        try {
            petPhotos = petPhotoMapper.selectAll(page, num);
        } catch (Exception e) {
            log.error("PetPhotoServiceImpl的findAll方法出错" + e.getMessage());
            return null;
        }
        return petPhotos;
    }

    @Override
    public int addInfo(PetPhoto petPhoto) {
        try {
            return petPhotoMapper.insertInfo(petPhoto);
        } catch (Exception e) {
            log.error("PetPhotoServiceImpl的addInfo方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateById(PetPhoto petPhoto) {
        try {
            return petPhotoMapper.updateById(petPhoto);
        } catch (Exception e) {
            log.error("PetPhotoServiceImpl的updateById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteById(String id) {
        try {
            return petPhotoMapper.deleteById(id);
        } catch (Exception e) {
            log.error("PetPhotoServiceImpl的deleteById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public PetPhoto findById(String id) {
        PetPhoto petPhoto = null;
        try {
            petPhoto = petPhotoMapper.selectById(id);
        } catch (Exception e) {
            log.error("PetPhotoServiceImpl的findById方法出错" + e.getMessage());
            return null;
        }
        return petPhoto;
    }

    @Override
    public String count() {
        try {
            return petPhotoMapper.count();
        } catch (Exception e) {
            log.error("PetPhotoServiceImpl的count方法出错" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<PetPhoto> findByPetInfoId(String id) {
        List<PetPhoto> petPhotoList = null;
        try {
            petPhotoList = petPhotoMapper.selectByPetInfoId(id);
        } catch (Exception e) {
            log.error("PetPhotoServiceImpl的findByPetInfoId方法出错" + e.getMessage());
        }
        return petPhotoList;
    }
}