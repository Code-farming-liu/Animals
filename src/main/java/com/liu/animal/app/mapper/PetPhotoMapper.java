package com.liu.animal.app.mapper;

import com.liu.animal.app.entity.PetPhoto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetPhotoMapper extends MapperPerent<PetPhoto>{
    List<PetPhoto> selectByPetInfoId(String id);
}
