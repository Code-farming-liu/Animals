package com.liu.animal.app.mapper;

import com.liu.animal.app.entity.SysSign;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignMapper extends MapperPerent<SysSign>{
    SysSign selectByUserId(String userId);
    int truncate();

    String selectYeaterday(String yesterday);
}
