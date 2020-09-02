package com.liu.animal.app.mapper;

import com.liu.animal.app.entity.PetHabit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetHabitMapper extends MapperPerent<PetHabit>{
    List<PetHabit> selectByPetInfoId(String id);
}
