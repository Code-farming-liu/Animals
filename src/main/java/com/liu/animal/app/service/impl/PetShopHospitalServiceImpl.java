package com.liu.animal.app.service.impl;

import com.liu.animal.app.entity.PetHospitalShopPhoto;
import com.liu.animal.app.entity.PetShopHospital;
import com.liu.animal.app.mapper.PetShopHospitalMapper;
import com.liu.animal.app.service.PetHospitalShopPhotoService;
import com.liu.animal.app.service.PetShopHospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: PetShopHospitalServiceImpl
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/7 20:50
 **/
@Service
@Slf4j
@Transactional
public class PetShopHospitalServiceImpl implements PetShopHospitalService {
    @Autowired
    private PetShopHospitalMapper petShopHospitalMapper;
    @Autowired
    private PetHospitalShopPhotoService petHospitalShopPhotoService;

    @Override
    public List<PetShopHospital> findAll(Integer page, Integer num) {
        List<PetShopHospital> petShopHospitals;
        try {
            if(page == null && num == null) {
                page = 0;
                num = Integer.parseInt(petShopHospitalMapper.count());
            }else {
                page = (page - 1) * num;
            }
            petShopHospitals = petShopHospitalMapper.selectAll(page, num);
        } catch (Exception e) {
            log.error("PetShopHospitalServiceImpl的findAll方法出错" + e.getMessage());
            return null;
        }
        for (PetShopHospital petShopHospital : petShopHospitals) {
            List<PetHospitalShopPhoto> petHospitalShopPhotoList = petHospitalShopPhotoService.findByPetShopHospitalId(petShopHospital.getId());
            petShopHospital.setPetHospitalShopPhotoList(petHospitalShopPhotoList);
        }
        return petShopHospitals;
    }

    @Override
    public int addInfo(PetShopHospital petShopHospital) {
        try {
             petShopHospitalMapper.insertInfo(petShopHospital);
        } catch (Exception e) {
            log.error("PetShopHospitalServiceImpl的addInfo方法出错" + e.getMessage());
            return 0;
        }
        List<PetHospitalShopPhoto> petHospitalShopPhotoList = petShopHospital.getPetHospitalShopPhotoList();
        for (PetHospitalShopPhoto petHospitalShopPhoto : petHospitalShopPhotoList) {
            petHospitalShopPhoto.setPetShopHospitalId(petShopHospital.getId());
            petHospitalShopPhotoService.addInfo(petHospitalShopPhoto);
        }
        return 1;
    }

    @Override
    public int updateById(PetShopHospital petShopHospital) {
        try {
            return petShopHospitalMapper.updateById(petShopHospital);
        } catch (Exception e) {
            log.error("PetShopHospitalServiceImpl的updateById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteById(String id) {
        try {
            return petShopHospitalMapper.deleteById(id);
        } catch (Exception e) {
            log.error("PetShopHospitalServiceImpl的deleteById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public PetShopHospital findById(String id) {
        PetShopHospital petShopHospital = null;
        try {
            petShopHospital = petShopHospitalMapper.selectById(id);
        } catch (Exception e) {
            log.error("" + e.getMessage());
            return null;
        }
        return petShopHospital;
    }
    @Override
    public String count() {
        try {
            return petShopHospitalMapper.count();
        } catch (Exception e) {
            log.error("PetShopHospitalServiceImpl的count出错" + e.getMessage());
            return "fail";
        }
    }
}