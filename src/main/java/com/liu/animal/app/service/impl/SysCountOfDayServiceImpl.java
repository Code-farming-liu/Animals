package com.liu.animal.app.service.impl;

import com.liu.animal.app.entity.SysCountOfDay;
import com.liu.animal.app.mapper.SysCountOfDayMapper;
import com.liu.animal.app.service.ArticleInfoService;
import com.liu.animal.app.service.SysCountOfDayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: SysCountOfDayServiceImpl
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/7 21:13
 **/
@Service
@Slf4j
@Transactional
public class SysCountOfDayServiceImpl implements SysCountOfDayService {
    @Autowired
    private SysCountOfDayMapper sysCountOfDayMapper;
    @Autowired
    private SysCountOfDayService sysCountOfDayService;
    @Autowired
    private ArticleInfoService articleInfoService;


    @Override
    public List<SysCountOfDay> findAll(Integer page, Integer num) {
        List<SysCountOfDay> sysCountOfDays = null;
        try {
            if (page == null && num == null) {
                page = 0;
                num = Integer.parseInt(sysCountOfDayMapper.count());
            } else {
                page = (page - 1) * num;
            }
            sysCountOfDays = sysCountOfDayMapper.selectAll(page, num);
        } catch (Exception e) {
            log.error("SysCountOfDayServiceImpl的findAll方法出错" + e.getMessage());
        }
        return sysCountOfDays;
    }

    @Override
    public int addInfo(SysCountOfDay sysCountOfDay) {
        try {
            return sysCountOfDayMapper.insertInfo(sysCountOfDay);
        } catch (Exception e) {
            log.error("SysCountOfDayServiceImpl的addInfo方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateById(SysCountOfDay sysCountOfDay) {
        try {
            return sysCountOfDayMapper.updateById(sysCountOfDay);
        } catch (Exception e) {
            log.error("SysCountOfDayServiceImpl的updateById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteById(String id) {
        try {
            return sysCountOfDayMapper.deleteById(id);
        } catch (Exception e) {
            log.error("SysCountOfDayServiceImpl的deleteById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public SysCountOfDay findById(String id) {
        SysCountOfDay sysCountOfDay = null;
        try {
            sysCountOfDay = sysCountOfDayMapper.selectById(id);
        } catch (Exception e) {
            log.error("SysCountOfDayServiceImpl的findById方法出错" + e.getMessage());
        }
        return sysCountOfDay;
    }

    @Override
    public String count() {
        try {
            return sysCountOfDayMapper.count();
        } catch (Exception e) {
            log.error("SysCountOfDayServiceImpl的count出错" + e.getMessage());
            return "fail";
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
//    @Scheduled(cron = "* * * * * ?")
    public void clearCountOfDay() {
        log.info("定时任务开启");
        try {
            sysCountOfDayMapper.truncate();
        } catch (Exception e) {
            log.error("SysCountOfDayServiceImpl的clearCountOfDay出错" + e.getMessage());
        }
        log.info("定时任务完成");
    }

    @Override
    public List<SysCountOfDay> findByUserId(String createUserId) {
        List<SysCountOfDay> sysCountOfDays = null;
        try {
            sysCountOfDays = sysCountOfDayMapper.selectByUserId(createUserId);
        } catch (Exception e) {
            log.error("SysCountOfDayServiceImpl的findByUserId出错" + e.getMessage());
            return null;
        }
        return sysCountOfDays;
    }
}