package com.liu.animal.app.mapper;

import com.liu.animal.app.entity.PetTaskPhoto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PetTaskPhotoMapper extends MapperPerent<PetTaskPhoto> {
    List<PetTaskPhoto> selectByPetTaskId(@Param("petTaskId") String petTaskId, @Param("type") String type);
}
