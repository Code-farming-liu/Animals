package com.liu.animal.app.service.impl;

import com.liu.animal.app.entity.PetHabit;
import com.liu.animal.app.entity.PetInfo;
import com.liu.animal.app.entity.PetPhoto;
import com.liu.animal.app.mapper.PetInfoMapper;
import com.liu.animal.app.service.PetHabitService;
import com.liu.animal.app.service.PetHospitalShopPhotoService;
import com.liu.animal.app.service.PetInfoService;
import com.liu.animal.app.service.PetPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: PetInfoServiceImpl
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/7 9:45
 **/
@Service
@Slf4j
@Transactional
public class PetInfoServiceImpl implements PetInfoService {
    @Autowired
    private PetInfoMapper petInfoMapper;
    @Autowired
    private PetHospitalShopPhotoService petHospitalShopPhotoService;
    @Autowired
    private PetHabitService petHabitService;
    @Autowired
    private PetPhotoService petPhotoService;
    @Autowired
    private PetInfoService petInfoService;

    @Override
    public List<PetInfo> findAll(Integer page, Integer num) {
        List<PetInfo> petInfos = null;
        try {
            if (page == null && num == null) {
                page = 0;
                num = Integer.parseInt(petInfoMapper.count());
            } else {
                page = (page - 1) * num;
            }
            petInfos = petInfoMapper.selectAll(page, num);
        } catch (Exception e) {
            log.error("PetInfoServiceImpl的方法出错" + e.getMessage());
        }
        for (PetInfo petInfo : petInfos) {
            List<PetPhoto> petPhotoList = petPhotoService.findByPetInfoId(petInfo.getId());
            petInfo.setPetPhotoList(petPhotoList);
            List<PetHabit> petHabitList = petHabitService.findByPetInfoId(petInfo.getId());
            petInfo.setHabitList(petHabitList);
        }
        return petInfos;
    }

    @Override
    public int addInfo(PetInfo petInfo) {
        try {
            petInfoMapper.insertInfo(petInfo);
        } catch (Exception e) {
            log.error("PetInfoServiceImpl的addInfo失败" + e.getMessage());
            return 0;
        }
        List<PetPhoto> petPhotoList = petInfo.getPetPhotoList();
        PetInfo petInfo1 = petInfoService.findEndInfo();
        if (petPhotoList != null) {
            for (PetPhoto petPhoto : petPhotoList) {
                petPhoto.setPetInfoId(petInfo1.getId());
                petPhotoService.addInfo(petPhoto);
            }
        }
        List<PetHabit> habitList = petInfo.getHabitList();
        if (habitList != null) {
            for (PetHabit petHabit : habitList) {
                petHabit.setPetInfoId(petInfo1.getId());
                petHabitService.addInfo(petHabit);
            }
        }
        return 1;
    }


    @Override
    public int updateById(PetInfo petInfo) {
        try {
            return petInfoMapper.updateById(petInfo);
        } catch (Exception e) {
            log.error("PetInfoServiceImpl的updateById失败" + e.getMessage());
            return 0;
        }

    }

    @Override
    public int deleteById(String id) {
        try {
            petInfoMapper.deleteById(id);
        } catch (Exception e) {
            log.error("PetInfoServiceImpl的deleteById失败" + e.getMessage());
            return 0;
        }
        return 1;
    }

    @Override
    public PetInfo findById(String id) {
        PetInfo petInfo = null;
        try {
            petInfo = petInfoMapper.selectById(id);
        } catch (Exception e) {
            log.error("PetInfoServiceImpl的findById失败" + e.getMessage());
            return null;
        }
        if (petInfo != null) {
            List<PetPhoto> petPhotoList = petPhotoService.findByPetInfoId(petInfo.getId());
            petInfo.setPetPhotoList(petPhotoList);
            List<PetHabit> petHabitList = petHabitService.findByPetInfoId(petInfo.getId());
            petInfo.setHabitList(petHabitList);
        }
        return petInfo;
    }

    @Override
    public String count() {
        try {
            return petInfoMapper.count();
        } catch (Exception e) {
            log.error("PetInfoServiceImpl的count出错" + e.getMessage());
            return "fail";
        }
    }

    @Override
    public List<PetInfo> findInfoByUserInfoId(String userId) {
        List<PetInfo> petInfos = null;
        List<PetInfo> petInfos1 = new ArrayList<>();
        try {
            petInfos = petInfoMapper.selectInfoByUserInfoId(userId);
        } catch (Exception e) {
            log.error("PetInfoServiceImpl的findInfoByUserInfoId出错" + e.getMessage());
            return null;
        }
        for (PetInfo petInfo : petInfos) {
            PetInfo petInfo1 = petInfoService.findById(petInfo.getId());
            petInfos1.add(petInfo1);
        }
        return petInfos1;
    }

    @Override
    public PetInfo findEndInfo() {
        try {
            return petInfoMapper.selectEndInfo();
        } catch (Exception e) {
            log.error("PetInfoServiceImpl的findEndInfo出错" + e.getMessage());
            return null;
        }
    }
}