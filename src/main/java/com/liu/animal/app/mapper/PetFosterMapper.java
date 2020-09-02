package com.liu.animal.app.mapper;

import com.liu.animal.app.entity.PetFoster;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetFosterMapper extends MapperPerent<PetFoster>{
    List<PetFoster> selectSuccessTaskByFromUserId(String userId);
    List<PetFoster> selectEndTaskByFromUserId(String userId);
    List<PetFoster> selectDoingTaskByFromUserId(String userId);

    PetFoster selectByEndInfo();
}
