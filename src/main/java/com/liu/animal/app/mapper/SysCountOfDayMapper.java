package com.liu.animal.app.mapper;

import com.liu.animal.app.entity.SysCountOfDay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysCountOfDayMapper extends MapperPerent<SysCountOfDay>{
    int truncate();

    List<SysCountOfDay> selectByUserId(String userId);

}
