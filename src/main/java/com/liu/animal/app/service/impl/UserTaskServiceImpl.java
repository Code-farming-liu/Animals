package com.liu.animal.app.service.impl;

import com.liu.animal.app.entity.PetFoster;
import com.liu.animal.app.entity.SysNotice;
import com.liu.animal.app.entity.UserTask;
import com.liu.animal.app.mapper.UserTaskMapper;
import com.liu.animal.app.service.PetAdoptService;
import com.liu.animal.app.service.PetFosterService;
import com.liu.animal.app.service.SysNoticeService;
import com.liu.animal.app.service.UserTaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: UserTaskServiceImpl
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/12 8:35
 **/
@Service
@Slf4j
@Transactional
public class UserTaskServiceImpl implements UserTaskService {
    @Autowired
    private UserTaskMapper userTaskMapper;
    @Autowired
    private UserTaskService userTaskService;
    @Autowired
    private SysNoticeService sysNoticeService;
    @Autowired
    private PetFosterService petFosterService;
    @Autowired
    private PetAdoptService petAdoptService;


    @Override
    public List<UserTask> findAll(Integer page, Integer num) {
        List<UserTask> userTasks = null;
        try {
            if (page == null && num == null) {
                page = 0;
                num = Integer.parseInt(userTaskMapper.count());
            } else {
                page = (page - 1) * num;
            }
            userTasks = userTaskMapper.selectAll(page, num);
        } catch (Exception e) {
            log.error("UserTaskServiceImpl的List<UserInfo> 失败" + e.getMessage());
            return null;
        }
        return userTasks;
    }

    @Override
    public int addInfo(UserTask userTask) {
        try {
            return userTaskMapper.insertInfo(userTask);
        } catch (Exception e) {
            log.error("UserTaskServiceImpl的addInfo方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateById(UserTask userTask) {
        int res = 0;
        try {
            res = userTaskMapper.updateById(userTask);
        } catch (Exception e) {
            log.error("UserTaskServiceImpl的updateById方法出错" + e.getMessage());
            return 0;
        }
        if (res != 0) {
            List<UserTask> userTaskList = userTaskMapper.selectWantUser(userTask.getTaskId(), userTask.getType());
            for (UserTask usertask : userTaskList) {
                if (usertask.getType().equals("1")) {
                    if(StringUtils.isNotBlank(userTask.getIsSuccess())){
                        if(userTask.getIsSuccess().equals("1")){
                            PetFoster petFoster = new PetFoster();
                            petFoster.setId(userTask.getTaskId());
                            petFoster.setType("1");
                            petFosterService.updateById(petFoster);
                        }
                    }
                    SysNotice sysNotice = new SysNotice();
                    sysNotice.setFromUserId(usertask.getFromUserId());
                    sysNotice.setToUserId(usertask.getToUserId());
                    sysNotice.setType("7");
                    sysNotice.setIsSuccess(usertask.getIsSuccess());
                    sysNotice.setCreateTime(new Date());
                    sysNotice.setArticleInfoId(userTask.getTaskId());
                    sysNoticeService.addInfo(sysNotice);
                } else if (usertask.getType().equals("2")) {
                    if(StringUtils.isNotBlank(userTask.getIsSuccess())){
                        if(userTask.getIsSuccess().equals("1")){
                            PetFoster petFoster = new PetFoster();
                            petFoster.setId(userTask.getTaskId());
                            petFoster.setType("1");
                            petFosterService.updateById(petFoster);
                        }
                    }
                    SysNotice sysNotice = new SysNotice();
                    sysNotice.setFromUserId(usertask.getFromUserId());
                    sysNotice.setToUserId(usertask.getToUserId());
                    sysNotice.setType("8");
                    sysNotice.setIsSuccess(usertask.getIsSuccess());
                    sysNotice.setCreateTime(new Date());
                    sysNotice.setArticleInfoId(userTask.getTaskId());
                    sysNoticeService.addInfo(sysNotice);
                }
            }
        }
        return 1;
    }

    @Override
    public int deleteById(String id) {
        try {
            return userTaskMapper.deleteById(id);
        } catch (Exception e) {
            log.error("UserTaskServiceImpl的deleteById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public UserTask findById(String id) {
        UserTask userTask = null;
        try {
            userTask = userTaskMapper.selectById(id);
        } catch (Exception e) {
            log.error("UserTaskServiceImpl的findById出错" + e.getMessage());
            return null;
        }
        return userTask;
    }

    @Override
    public String count() {
        try {
            return userTaskMapper.count();
        } catch (Exception e) {
            log.error("UserTaskServiceImpl的count出错" + e.getMessage());
            return "fail";
        }

    }

    @Override
    public List<UserTask> findByFromUserId(String fromUserId) {
        List<UserTask> userTasks = null;
        try {
            userTasks = userTaskMapper.selectByFromUserId(fromUserId);
        } catch (Exception e) {
            log.error("UserTaskServiceImpl的findByFromUserId出错" + e.getMessage());
            return null;
        }
        return userTasks;
    }

    @Override
    public List<UserTask> selectWantUser(String taskId, String type) {
        List<UserTask> userTasks = null;
        try {
            userTasks = userTaskMapper.selectWantUser(taskId, type);
        } catch (Exception e) {
            log.error("UserTaskServiceImpl的selectWantUser出错" + e.getMessage());
            return null;
        }
        return userTasks;
    }

    @Override
    public List<UserTask> findTaskByTaskId(String id, String type) {
        try {
            return userTaskMapper.selectWantUser(id, type);
        } catch (Exception e) {
            log.error("UserTaskServiceImpl的findTaskByTaskId出错" + e.getMessage());
            return null;
        }
    }
}