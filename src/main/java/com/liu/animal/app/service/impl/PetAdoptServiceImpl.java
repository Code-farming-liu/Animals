package com.liu.animal.app.service.impl;

import com.liu.animal.app.entity.*;
import com.liu.animal.app.mapper.PetAdoptMapper;
import com.liu.animal.app.service.*;
import com.liu.animal.base.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName: PetAdoptServiceImpl
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/7 8:39
 **/
@Service
@Slf4j
@Transactional
public class PetAdoptServiceImpl implements PetAdoptService {
    @Autowired
    private PetAdoptMapper petAdoptMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private PetInfoService petInfoService;
    @Autowired
    private PetAdoptService petAdoptService;
    @Autowired
    private SysNoticeService sysNoticeService;
    @Autowired
    private UserTaskService userTaskService;
    @Autowired
    private PetTaskPhotoService petTaskPhotoService;

    @Override
    public List<PetAdopt> findAll(Integer page, Integer num) {
        List<PetAdopt> petAdopts = null;
        List<PetAdopt> petAdoptList = new ArrayList<>();
        int total = Integer.parseInt(petAdoptMapper.count());
        try {
            if (page == null && num == null) {
                page = 0;
                num = total;
            } else {
                page = (page - 1) * num;
            }
            petAdopts = petAdoptMapper.selectAll(page, num);
        } catch (Exception e) {
            log.error("PetAdoptServiceImpl的findAll方法出错" + e.getMessage());
            return null;
        }
        for (PetAdopt petAdopt : petAdopts) {
            Date now = new Date();
            Date endDate = petAdopt.getEndDate();
            if (DateUtils.compareDate(now, endDate) > 0) {
                if (petAdopt.getType().equals("3")) {
                    continue;
                }
                PetAdopt petAdopt1 = new PetAdopt();
                petAdopt1.setType("3");
                petAdopt1.setId(petAdopt.getId());
                petAdoptService.updateById(petAdopt1);
                continue;
            }
            if (petAdopt.getType().equals("2")) {
                petAdopt.setCountOfPage(petAdopts.size());
                if (StringUtils.isNotBlank(petAdopt.getFromUserId())) {
                    UserInfo userInfo = userService.findById(petAdopt.getFromUserId());
                    petAdopt.setUserInfo(userInfo);
                }
                PetInfo petInfo = petInfoService.findById(petAdopt.getPetInfoId());
                petAdopt.setPetInfo(petInfo);
                List<PetTaskPhoto> petTaskPhotoList = petTaskPhotoService.findPhoto(petAdopt.getId(), petAdopt.getPetType());
                petAdopt.setPetTaskPhotoList(petTaskPhotoList);
                petAdoptList.add(petAdopt);
            }
        }
        Collections.sort(petAdoptList, new Comparator<PetAdopt>() {
            @Override
            public int compare(PetAdopt o1, PetAdopt o2) {
                return (int) (o2.getCreateDate().getTime() - o1.getCreateDate().getTime());
            }
        });
        return petAdoptList;
    }

    @Override
    public int addInfo(PetAdopt petAdopts) {
        petAdopts.setCreateDate(new Date());
        try {
            petAdoptMapper.insertInfo(petAdopts);
        } catch (Exception e) {
            log.error("PetAdoptServiceImpl的addInfo出错" + e.getMessage());
            return 0;
        }
        PetAdopt petAdopt = petAdoptMapper.selectByEndInfo();
        List<PetTaskPhoto> petTaskPhotoList = petAdopts.getPetTaskPhotoList();
        if (petTaskPhotoList != null) {
            for (PetTaskPhoto petTaskPhoto : petTaskPhotoList) {
                petTaskPhoto.setType("2");
                petTaskPhoto.setPetTaskId(petAdopt.getId());
                petTaskPhotoService.addPhoto(petTaskPhoto);
            }
        }
        return 1;
    }

    @Override
    public int updateById(PetAdopt petAdopts) {
        try {
            petAdoptMapper.updateById(petAdopts);
        } catch (Exception e) {
            log.error("PetAdoptServiceImpl的updateById出错" + e.getMessage());
            return 0;
        }
        String evaluate = petAdopts.getEvaluate();
        if (StringUtils.isNotBlank(evaluate)) {
            PetAdopt petAdopt = petAdoptService.findById(petAdopts.getId());
            UserInfo userInfo = userService.findById(petAdopt.getToUserId());
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
            userInfo1.setId(petAdopt.getToUserId());
            userInfo1.setIntegral(integral);
            userService.updateById(userInfo1);
        }
        return 1;
    }

    @Override
    public int deleteById(String id) {
        try {
            return petAdoptMapper.deleteById(id);
        } catch (Exception e) {
            log.error("PetAdoptServiceImpl的deleteById出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public PetAdopt findById(String id) {
        PetAdopt petAdopts = null;
        try {
            petAdopts = petAdoptMapper.selectById(id);
        } catch (Exception e) {
            log.error("PetAdoptServiceImpl的 findById 失败" + e.getMessage());
            return null;
        }
        UserInfo userInfo = userService.findById(petAdopts.getFromUserId());
        petAdopts.setUserInfo(userInfo);
        PetInfo petInfo = petInfoService.findById(petAdopts.getPetInfoId());
        petAdopts.setPetInfo(petInfo);
        List<PetTaskPhoto> petTaskPhotoList = petTaskPhotoService.findPhoto(petAdopts.getId(), petAdopts.getPetType());
        petAdopts.setPetTaskPhotoList(petTaskPhotoList);
        return petAdopts;
    }

    @Override
    public String count() {
        try {
            return petAdoptMapper.count();
        } catch (Exception e) {
            log.error("PetAdoptServiceImpl的count出错" + e.getMessage());
            return "fail";
        }
    }

    @Override
    public List<PetAdopt> findByFromUserId(String userId) {
        List<PetAdopt> petAdopts = null;
        List<PetAdopt> petAdopts1 = new ArrayList<>();
        try {
            petAdopts = petAdoptMapper.selectDoingTaskByFromUserId(userId);
        } catch (Exception e) {
            log.error("PetAdoptServiceImpl的findByFromUserId出错" + e.getMessage());
            return null;
        }
        for (PetAdopt petAdopt : petAdopts) {
            Date now = new Date();
            Date endDate = petAdopt.getEndDate();
            if (DateUtils.compareDate(now, endDate) > 0) {
                if (petAdopt.getType().equals("3")) {
                    continue;
                }
                PetAdopt petAdopt1 = new PetAdopt();
                petAdopt1.setType("3");
                petAdopt1.setId(petAdopt.getId());
                petAdoptService.updateById(petAdopt1);
                continue;
            }
            UserInfo userInfo = userService.findById(petAdopt.getToUserId());
            petAdopt.setUserInfo(userInfo);
            PetInfo petInfo = petInfoService.findById(petAdopt.getPetInfoId());
            petAdopt.setPetInfo(petInfo);
            List<UserTask> userTaskList = userTaskService.findTaskByTaskId(petAdopt.getId(), petAdopt.getType());
            List<PetTaskPhoto> petTaskPhotoList = petTaskPhotoService.findPhoto(petAdopt.getId(), petAdopt.getPetType());
            petAdopt.setPetTaskPhotoList(petTaskPhotoList);
            if (userTaskList != null) {
                for (UserTask userTask : userTaskList) {
                    if (userTask.getIsSuccess().equals("1")) {
                        petAdopt.setIsSuccess("1");
                    }
                }
            }
            petAdopts1.add(petAdopt);
        }
        Collections.sort(petAdopts1, new Comparator<PetAdopt>() {
            @Override
            public int compare(PetAdopt o1, PetAdopt o2) {
                return (int) (o2.getCreateDate().getTime() - o1.getCreateDate().getTime());
            }
        });
        return petAdopts1;
    }

    @Override
    public List<PetAdopt> findByToUserId(String userId) {
        List<PetAdopt> petAdopts = null;
        try {
            petAdopts = petAdoptMapper.selectSuccessTaskByFromUserId(userId);
        } catch (Exception e) {
            log.error("PetAdoptServiceImpl的findByToUserId出错" + e.getMessage());
            return null;
        }
        for (PetAdopt petAdopt : petAdopts) {
            Date now = new Date();
            Date endDate = petAdopt.getEndDate();
            if (DateUtils.compareDate(now, endDate) > 0) {
                if (petAdopt.getType().equals("3")) {
                    continue;
                }
                PetAdopt petAdopt1 = new PetAdopt();
                petAdopt1.setType("3");
                petAdopt1.setId(petAdopt.getId());
                petAdoptService.updateById(petAdopt1);
                continue;
            }
            UserInfo userInfo = userService.findById(petAdopt.getFromUserId());
            petAdopt.setUserInfo(userInfo);
            PetInfo petInfo = petInfoService.findById(petAdopt.getPetInfoId());
            petAdopt.setPetInfo(petInfo);
            List<PetTaskPhoto> petTaskPhotoList = petTaskPhotoService.findPhoto(petAdopt.getId(), petAdopt.getPetType());
            petAdopt.setPetTaskPhotoList(petTaskPhotoList);
        }
        Collections.sort(petAdopts, new Comparator<PetAdopt>() {
            @Override
            public int compare(PetAdopt o1, PetAdopt o2) {
                return (int) (o2.getCreateDate().getTime() - o1.getCreateDate().getTime());
            }
        });
        return petAdopts;
    }

    @Override
    public String updateStateFoster(PetAdopt petAdopt) {
        int res = petAdoptService.updateById(petAdopt);
        if (res == 1) {
            SysNotice sysNotice = new SysNotice();
            sysNotice.setFromUserId(petAdopt.getFromUserId());
            sysNotice.setToUserId(petAdopt.getToUserId());
            sysNotice.setType("8");
            sysNotice.setIsSuccess(petAdopt.getType());
            int res1 = sysNoticeService.addInfo(sysNotice);
            if (res1 == 1) {
                return "success";
            }
        }
        return "fail";
    }

    @Override
    public List<PetAdopt> findEndByToUserId(String userId) {

        List<PetAdopt> petAdopts = null;
        try {
            petAdopts = petAdoptMapper.selectEndTaskByFromUserId(userId);
        } catch (Exception e) {
            log.error("PetAdoptServiceImpl的findByToUserId出错" + e.getMessage());
            return null;
        }
        for (PetAdopt petAdopt : petAdopts) {
//            Date now = new Date();
//            Date endDate = petAdopt.getEndDate();
//            if (DateUtils.compareDate(now, endDate) > 0) {
//                if (petAdopt.getType().equals("3")) {
//                    continue;
//                }
//                PetAdopt petAdopt1 = new PetAdopt();
//                petAdopt1.setType("3");
//                petAdopt1.setId(petAdopt.getId());
//                petAdoptService.updateById(petAdopt1);
//                continue;
//            }
            UserInfo userInfo = userService.findById(petAdopt.getFromUserId());
            petAdopt.setUserInfo(userInfo);
            PetInfo petInfo = petInfoService.findById(petAdopt.getPetInfoId());
            petAdopt.setPetInfo(petInfo);
            List<PetTaskPhoto> petTaskPhotoList = petTaskPhotoService.findPhoto(petAdopt.getId(), petAdopt.getPetType());
            petAdopt.setPetTaskPhotoList(petTaskPhotoList);
        }
        Collections.sort(petAdopts, new Comparator<PetAdopt>() {
            @Override
            public int compare(PetAdopt o1, PetAdopt o2) {
                return (int) (o2.getCreateDate().getTime() - o1.getCreateDate().getTime());
            }
        });
        return petAdopts;
    }
}