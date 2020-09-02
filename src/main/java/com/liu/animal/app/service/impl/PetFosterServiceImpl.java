package com.liu.animal.app.service.impl;

import com.liu.animal.app.entity.*;
import com.liu.animal.app.mapper.PetFosterMapper;
import com.liu.animal.app.service.*;
import com.liu.animal.base.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName: PetFosterServiceImpl
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/7 8:39
 **/
@Service
@Slf4j
@Transactional
public class PetFosterServiceImpl implements PetFosterService {
    @Autowired
    private PetFosterMapper petFosterMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private PetInfoService petInfoService;
    @Autowired
    private UserTaskService userTaskService;
    @Autowired
    private PetFosterService petFosterService;
    @Autowired
    private SysNoticeService sysNoticeService;
    @Autowired
    private ArticleInfoService articleInfoService;
    @Autowired
    private PetTaskPhotoService petTaskPhotoService;
    @Autowired
    private PetAdoptService petAdoptService;

    @Override
    public List<PetFoster> findAll(Integer page, Integer num) {
        List<PetFoster> petFosters = null;
        List<PetFoster> petFosterList = new ArrayList<>();
        int total = Integer.parseInt(petFosterMapper.count());
        try {
            if (page == null && num == null) {
                page = 0;
                num = total;
            } else {
                page = (page - 1) * num;
            }
            petFosters = petFosterMapper.selectAll(page, num);
        } catch (Exception e) {
            log.error("PetFosterServiceImpl的findAll方法出错" + e.getMessage());
            return null;
        }
        for (PetFoster petFoster : petFosters) {
            Date now = new Date();
            Date endDate = petFoster.getEndDate();
            if (DateUtils.compareDate(now, endDate) > 0) {
                if (petFoster.getType().equals("0")) {
                    continue;
                }
                PetFoster petFoster1 = new PetFoster();
                petFoster1.setType("0");
                petFoster1.setId(petFoster.getId());
                petFosterService.updateById(petFoster1);
                continue;
            }
            if (petFoster.getType().equals("2")) {
                petFoster.setCountOfPage(petFosters.size());
                if (StringUtils.isNotBlank(petFoster.getFromUserId())) {
                    UserInfo userInfo = userService.findById(petFoster.getFromUserId());
                    petFoster.setUserInfo(userInfo);
                }
                PetInfo petInfo = petInfoService.findById(petFoster.getPetInfoId());
                petFoster.setPetInfo(petInfo);
                List<PetTaskPhoto> petTaskPhotoList = petTaskPhotoService.findPhoto(petFoster.getId(), petFoster.getPetType());
                petFoster.setPetTaskPhotoList(petTaskPhotoList);
                petFosterList.add(petFoster);
            }
        }
        Collections.sort(petFosterList, new Comparator<PetFoster>() {
            @Override
            public int compare(PetFoster o1, PetFoster o2) {
                return (int) (o2.getCreateDate().getTime() - o1.getCreateDate().getTime());
            }
        });
        return petFosterList;
    }

    @Override
    public int addInfo(PetFoster petFosters) {
        UserInfo userInfo = userService.findById(petFosters.getFromUserId());
        List<UserTask> userTaskList = userTaskService.findByFromUserId(petFosters.getFromUserId());
        if (userTaskList.size() > 1) {
            if (userInfo.getVip().equals("0")) {
                return 2;
            }
        }
        petFosters.setCreateDate(new Date());
        try {
            petFosterMapper.insertInfo(petFosters);
        } catch (Exception e) {
            log.error("PetFosterServiceImpl的addInfo出错" + e.getMessage());
            return 0;
        }
        PetFoster petFoster = petFosterMapper.selectByEndInfo();
        List<PetTaskPhoto> petTaskPhotoList = petFosters.getPetTaskPhotoList();
        if (petTaskPhotoList != null) {
            for (PetTaskPhoto petTaskPhoto : petTaskPhotoList) {
                petTaskPhoto.setType("1");
                petTaskPhoto.setPetTaskId(petFoster.getId());
                petTaskPhotoService.addPhoto(petTaskPhoto);
            }
        }

        return 1;
    }

    @Override
    public int updateById(PetFoster petFosters) {
        try {
            petFosterMapper.updateById(petFosters);
        } catch (Exception e) {
            log.error("PetFosterServiceImpl的updateById出错" + e.getMessage());
            return 0;
        }
        String evaluate = petFosters.getEvaluate();
        if (StringUtils.isNotBlank(evaluate)) {
            PetFoster petFoster = petFosterService.findById(petFosters.getId());
            UserInfo userInfo = userService.findById(petFoster.getToUserId());
            UserInfo userInfo1 = new UserInfo();
            Integer integral = userInfo.getIntegral();
            if (evaluate.equals("5")) {
                integral += 3;
            } else if (evaluate.equals("4")) {
                integral += 2;
            } else if (evaluate.equals("3")) {
                integral += 1;
            } else if (evaluate.equals("1")) {
                integral -= 1;
            }
            userInfo1.setId(petFoster.getToUserId());
            userInfo1.setIntegral(integral);
            userService.updateById(userInfo1);
        }
        return 1;
    }

    @Override
    public int deleteById(String id) {
        try {
            return petFosterMapper.deleteById(id);
        } catch (Exception e) {
            log.error("PetFosterServiceImpl的deleteById出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public PetFoster findById(String id) {
        PetFoster PetFoster = null;
        try {
            PetFoster = petFosterMapper.selectById(id);
        } catch (Exception e) {
            log.error("PetFosterServiceImpl的 findById 失败" + e.getMessage());
            return null;
        }
        UserInfo userInfo = userService.findById(PetFoster.getFromUserId());
        PetFoster.setUserInfo(userInfo);
        PetInfo petInfo = petInfoService.findById(PetFoster.getPetInfoId());
        PetFoster.setPetInfo(petInfo);
        List<PetTaskPhoto> petTaskPhotoList = petTaskPhotoService.findPhoto(PetFoster.getId(), PetFoster.getPetType());
        PetFoster.setPetTaskPhotoList(petTaskPhotoList);
        return PetFoster;
    }

    @Override
    public String count() {
        try {
            return petFosterMapper.count();
        } catch (Exception e) {
            log.error("PetFosterServiceImpl的count出错" + e.getMessage());
            return "fail";
        }
    }


    @Override
    public List<PetFoster> findByFromUserId(String userId) {
        List<PetFoster> petFosters = null;
        List<PetFoster> petFosters1 = new ArrayList<>();
        try {
            petFosters = petFosterMapper.selectDoingTaskByFromUserId(userId);
        } catch (Exception e) {
            log.error("PetFosterServiceImpl的findByFromUserId出错" + e.getMessage());
            return null;
        }
        for (PetFoster petFoster : petFosters) {
            Date now = new Date();
            Date endDate = petFoster.getEndDate();
            if (DateUtils.compareDate(now, endDate) > 0) {
                if (petFoster.getType().equals("3")) {
                    continue;
                }
                PetFoster petFoster1 = new PetFoster();
                petFoster1.setType("3");
                petFoster1.setId(petFoster.getId());
                petFosterService.updateById(petFoster1);
                continue;
            }
            UserInfo userInfo = userService.findById(petFoster.getToUserId());
            petFoster.setUserInfo(userInfo);
            PetInfo petInfo = petInfoService.findById(petFoster.getPetInfoId());
            petFoster.setPetInfo(petInfo);
            List<UserTask> userTaskList = userTaskService.findTaskByTaskId(petFoster.getId(), petFoster.getType());
            List<PetTaskPhoto> petTaskPhotoList = petTaskPhotoService.findPhoto(petFoster.getId(), petFoster.getPetType());
            petFoster.setPetTaskPhotoList(petTaskPhotoList);
            if (userTaskList.size() != 0) {
                for (UserTask userTask : userTaskList) {
                    if (userTask.getIsSuccess().equals("1")) {
                        petFoster.setIsSuccess("1");
                    }
                }
            }
            petFosters1.add(petFoster);
//            Date now = new Date();
//            Date endDate = petFoster.getEndDate();
//            if (DateUtils.compareDate(now, endDate) > 0) {
//                petFoster.setType("4");
//            }
        }
        Collections.sort(petFosters1, new Comparator<PetFoster>() {
            @Override
            public int compare(PetFoster o1, PetFoster o2) {
                return (int) (o2.getCreateDate().getTime() - o1.getCreateDate().getTime());
            }
        });
        return petFosters1;
    }

    @Override
    public List<PetFoster> findByToUserId(String userId) {
        List<PetFoster> petFosters = null;
        try {
            petFosters = petFosterMapper.selectSuccessTaskByFromUserId(userId);
        } catch (Exception e) {
            log.error("PetFosterServiceImpl的findByToUserId出错" + e.getMessage());
            return null;
        }
        for (PetFoster petFoster : petFosters) {
            Date now = new Date();
            Date endDate = petFoster.getEndDate();
            if (DateUtils.compareDate(now, endDate) > 0) {
                if (petFoster.getType().equals("3")) {
                    continue;
                }
                PetFoster petFoster1 = new PetFoster();
                petFoster1.setType("3");
                petFoster1.setId(petFoster.getId());
                petFosterService.updateById(petFoster1);
                continue;
            }
            UserInfo userInfo = userService.findById(petFoster.getFromUserId());
            petFoster.setUserInfo(userInfo);
            PetInfo petInfo = petInfoService.findById(petFoster.getPetInfoId());
            petFoster.setPetInfo(petInfo);
            List<PetTaskPhoto> petTaskPhotoList = petTaskPhotoService.findPhoto(petFoster.getId(), petFoster.getPetType());
            petFoster.setPetTaskPhotoList(petTaskPhotoList);
//            Date now = new Date();
//            Date endDate = petFoster.getEndDate();
//            if (DateUtils.compareDate(now, endDate) > 0) {
//                petFoster.setType("4");
//            }
        }
        Collections.sort(petFosters, new Comparator<PetFoster>() {
            @Override
            public int compare(PetFoster o1, PetFoster o2) {
                return (int) (o2.getCreateDate().getTime() - o1.getCreateDate().getTime());
            }
        });
        return petFosters;
    }

    @Override
    public String updateStateFoster(PetFoster petFoster) {
        int res = petFosterService.updateById(petFoster);
        if (res == 1) {
            SysNotice sysNotice = new SysNotice();
            sysNotice.setFromUserId(petFoster.getFromUserId());
            sysNotice.setToUserId(petFoster.getToUserId());
            sysNotice.setType("7");
            sysNotice.setIsSuccess(petFoster.getType());
            int res1 = sysNoticeService.addInfo(sysNotice);
            if (res1 == 1) {
                return "success";
            }
        }
        return "fail";
    }

    @Override
    public List<PetFoster> findEndByToUserId(String userId) {
        List<PetFoster> petFosters = null;
        try {
            petFosters = petFosterMapper.selectEndTaskByFromUserId(userId);
        } catch (Exception e) {
            log.error("PetFosterServiceImpl的findByToUserId出错" + e.getMessage());
            return null;
        }
        for (PetFoster petFoster : petFosters) {
            UserInfo userInfo = userService.findById(petFoster.getFromUserId());
            petFoster.setUserInfo(userInfo);
            PetInfo petInfo = petInfoService.findById(petFoster.getPetInfoId());
            petFoster.setPetInfo(petInfo);
            List<PetTaskPhoto> petTaskPhotoList = petTaskPhotoService.findPhoto(petFoster.getId(), petFoster.getPetType());
            petFoster.setPetTaskPhotoList(petTaskPhotoList);
        }
        Collections.sort(petFosters, new Comparator<PetFoster>() {
            @Override
            public int compare(PetFoster o1, PetFoster o2) {
                return (int) (o2.getCreateDate().getTime() - o1.getCreateDate().getTime());
            }
        });
        return petFosters;
    }

    @Override
    public List<UserTask> findWantUser(String taskId, String type) {
        List<UserTask> userTasks = null;
        try {
            userTasks = userTaskService.selectWantUser(taskId, type);
        } catch (Exception e) {
            log.error("PetFosterServiceImpl的findWantUser出错" + e.getMessage());
            return null;
        }
        for (UserTask userTask : userTasks) {
            if (userTask.getIsSuccess().equals("1")) {
                List<UserTask> userTaskList = new ArrayList<>();
                UserInfo userInfo = userService.findById(userTask.getFromUserId());
                userTask.setUserInfo(userInfo);
                List<PetInfo> petInfoList = petInfoService.findInfoByUserInfoId(userTask.getFromUserId());
                userTask.setPetInfoList(petInfoList);
                List<ArticleInfo> articleInfoList = articleInfoService.findArticleByCreateUserId(userTask.getFromUserId());
                userTask.setArticleInfo(articleInfoList);
                if (userTask.getType().equals("1")) {
                    PetFoster petFoster = petFosterService.findById(userTask.getTaskId());
                    userTask.setPetFoster(petFoster);
                } else if (userTask.getType().equals("2")){
                    PetAdopt petAdopt = petAdoptService.findById(userTask.getTaskId());
                    userTask.setPetAdopt(petAdopt);
                }
                userTaskList.add(userTask);
                return userTaskList;
            }
        }
        for (UserTask userTask : userTasks) {
            UserInfo userInfo = userService.findById(userTask.getFromUserId());
            userTask.setUserInfo(userInfo);
            List<PetInfo> petInfoList = petInfoService.findInfoByUserInfoId(userTask.getToUserId());
            userTask.setPetInfoList(petInfoList);
            List<ArticleInfo> articleInfoList = articleInfoService.findArticleByCreateUserId(userTask.getFromUserId());
            userTask.setArticleInfo(articleInfoList);
            if (userTask.getType().equals("1")) {
                PetFoster petFoster = petFosterService.findById(userTask.getTaskId());
                userTask.setPetFoster(petFoster);
            } else if (userTask.getType().equals("2")){
                PetAdopt petAdopt = petAdoptService.findById(userTask.getTaskId());
                userTask.setPetAdopt(petAdopt);
            }
        }

        return userTasks;
    }

}