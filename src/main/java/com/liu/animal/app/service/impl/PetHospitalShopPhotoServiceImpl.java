package com.liu.animal.app.service.impl;

import com.liu.animal.app.entity.PetHospitalShopPhoto;
import com.liu.animal.app.mapper.PetHospitalShopPhotoMapper;
import com.liu.animal.app.service.PetHospitalShopPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: PetHospitalShopPhotoServiceImpl
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/12 15:56
 **/
@Service
@Transactional
@Slf4j
public class PetHospitalShopPhotoServiceImpl implements PetHospitalShopPhotoService {
    @Autowired
    private PetHospitalShopPhotoMapper petHospitalShopPhotoMapper;

    @Override
    public List<PetHospitalShopPhoto> findAll(Integer page, Integer num) {
        List<PetHospitalShopPhoto> petHospitalShopPhotos = null;
        try {
            if (page == null && num == null) {
                page = 0;
                num = Integer.parseInt(petHospitalShopPhotoMapper.count());
            } else {
                page = (page - 1) * num;
            }
            petHospitalShopPhotos = petHospitalShopPhotoMapper.selectAll(page, num);
        } catch (Exception e) {
            log.error("PetHospitalShopPhotoServiceImpl的findAll方法出错" + e.getMessage());
            return null;
        }
        return petHospitalShopPhotos;
    }

    @Override
    public int addInfo(PetHospitalShopPhoto petHospitalShopPhoto) {
        try {
            return petHospitalShopPhotoMapper.insertInfo(petHospitalShopPhoto);
        } catch (Exception e) {
            log.error("PetHospitalShopPhotoServiceImpl的addInfo方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateById(PetHospitalShopPhoto petHospitalShopPhoto) {
        try {
            return petHospitalShopPhotoMapper.updateById(petHospitalShopPhoto);
        } catch (Exception e) {
            log.error("PetHospitalShopPhotoServiceImpl的updateById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteById(String id) {
        try {
            return petHospitalShopPhotoMapper.deleteById(id);
        } catch (Exception e) {
            log.error("PetHospitalShopPhotoServiceImpl的deleteById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public PetHospitalShopPhoto findById(String id) {
        try {
            return petHospitalShopPhotoMapper.selectById(id);
        } catch (Exception e) {
            log.error("PetHospitalShopPhotoServiceImpl的findById方法出错" + e.getMessage());
            return null;
        }
    }

    @Override
    public String count() {
        try {
            return petHospitalShopPhotoMapper.count();
        } catch (Exception e) {
            log.error("PetHospitalShopPhotoServiceImpl的count方法出错" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<PetHospitalShopPhoto> findByPetShopHospitalId(String id) {
        try {
return petHospitalShopPhotoMapper.selectByPetShopHostialId(id);
        } catch (Exception e) {
            log.error("" + e.getMessage());
        }
        return null;
    }
}