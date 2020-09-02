package com.liu.animal.app.service;

import com.liu.animal.app.entity.UserTask;

import java.util.List;

public interface UserTaskService extends ServiceParent<UserTask> {
    List<UserTask> findByFromUserId(String fromUserId);

    List<UserTask> selectWantUser(String taskId,String type);

    List<UserTask> findTaskByTaskId(String id, String type);
}
