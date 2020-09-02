package com.liu.animal.app.service;

import com.liu.animal.app.entity.PetFoster;
import com.liu.animal.app.entity.UserTask;

import java.util.List;

public interface PetFosterService extends ServiceParent<PetFoster>{
    List<PetFoster> findByFromUserId(String userId);

    List<PetFoster> findByToUserId(String userId);

    String updateStateFoster(PetFoster petFoster);

    List<PetFoster> findEndByToUserId(String userId);

    List<UserTask> findWantUser(String taskId,String type);
}
