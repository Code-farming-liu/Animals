package com.liu.animal.app.mapper;

import com.liu.animal.app.entity.UserTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserTaskMapper extends MapperPerent<UserTask> {
    List<UserTask> selectByFromUserId(String id);

    List<UserTask> selectWantUser(@Param("taskId") String taskId,@Param("type") String type);

}
