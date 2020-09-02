package com.liu.animal.app.service;

import com.liu.animal.app.entity.PetHabit;

import java.util.List;

public interface PetHabitService extends ServiceParent<PetHabit>{
    List<PetHabit> findByPetInfoId(String id);
}
