package com.liu.animal.app.service;

import com.liu.animal.app.entity.PetInfo;

import java.util.List;

public interface PetInfoService extends ServiceParent<PetInfo>{
    List<PetInfo> findInfoByUserInfoId(String userId);

    PetInfo findEndInfo();
}
