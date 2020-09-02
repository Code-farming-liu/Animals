package com.liu.animal.app.service;

import com.liu.animal.app.entity.PetAdopt;

import java.util.List;

public interface PetAdoptService extends ServiceParent<PetAdopt>{
    List<PetAdopt> findByFromUserId(String userId);

    List<PetAdopt> findByToUserId(String userId);

    String updateStateFoster(PetAdopt petAdopt);

    List<PetAdopt> findEndByToUserId(String userId);
}
