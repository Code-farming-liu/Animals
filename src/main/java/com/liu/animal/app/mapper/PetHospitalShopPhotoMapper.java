package com.liu.animal.app.mapper;

import com.liu.animal.app.entity.PetHospitalShopPhoto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetHospitalShopPhotoMapper extends MapperPerent<PetHospitalShopPhoto>{
    List<PetHospitalShopPhoto> selectByPetShopHostialId(String id);
}
