package com.liu.animal.app.mapper;

import com.liu.animal.app.entity.SysNotice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysNoticeMapper extends MapperPerent<SysNotice>{
    List<SysNotice> selectByUserId(String userId);

    SysNotice selectEndNotice();
}
