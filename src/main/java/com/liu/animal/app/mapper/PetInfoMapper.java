package com.liu.animal.app.mapper;

import com.liu.animal.app.entity.PetInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetInfoMapper extends MapperPerent<PetInfo>{
    List<PetInfo> selectInfoByUserInfoId(String userId);

    PetInfo selectEndInfo();
}
