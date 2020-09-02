package com.liu.animal.app.mapper;

import com.liu.animal.app.entity.PetAdopt;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetAdoptMapper extends MapperPerent<PetAdopt>{
    List<PetAdopt> selectSuccessTaskByFromUserId(String userId);
    List<PetAdopt> selectEndTaskByFromUserId(String userId);
    List<PetAdopt> selectDoingTaskByFromUserId(String userId);
    PetAdopt selectByEndInfo();
}
