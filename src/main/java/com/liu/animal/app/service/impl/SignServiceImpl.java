package com.liu.animal.app.service.impl;

import com.liu.animal.app.entity.SysSign;
import com.liu.animal.app.entity.UserInfo;
import com.liu.animal.app.mapper.SignMapper;
import com.liu.animal.app.service.SignService;
import com.liu.animal.app.service.UserService;
import com.liu.animal.base.util.DateUtils;
import com.liu.animal.base.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: SignServiceImpl
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/5 11:55
 **/
@Service
@Transactional
@Slf4j
public class SignServiceImpl implements SignService {
    @Autowired
    private SignMapper signMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private SignService signService;


    @Override
    public List<SysSign> findAll(Integer page, Integer num) {
        List<SysSign> sysSigns = null;
        try {
            if (page == null && num == null) {
                page = 0;
                num = Integer.parseInt(signMapper.count());
            } else {
                page = (page - 1) * num;
            }
            sysSigns = signMapper.selectAll(page, num);
        } catch (Exception e) {
            log.error("SignServiceImpl的findAll方法出错" + e.getMessage());
            return null;
        }
        return sysSigns;
    }

    @Override
    public int addInfo(SysSign sysSign) {
        try {
            return signMapper.insertInfo(sysSign);
        } catch (Exception e) {
            log.error("SignServiceImpl的addInfo方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateById(SysSign sysSign) {
        try {
            return signMapper.updateById(sysSign);
        } catch (Exception e) {
            log.error("SignServiceImpl的updateById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteById(String id) {
        try {
            return signMapper.deleteById(id);
        } catch (Exception e) {
            log.error("SignServiceImpl的deleteById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public SysSign findById(String id) {
        SysSign sysSign = null;
        try {
            sysSign = signMapper.selectById(id);
        } catch (Exception e) {
            log.error("SignServiceImpl的findById方法出错" + e.getMessage());
            return null;
        }
        return sysSign;

    }

    @Override
    public SysSign findByUserId(String id) {
        SysSign sysSign = null;
        Jedis jedis = redisUtil.getJedis();
        UserInfo userInfo = userService.findById(id);
        String key = userInfo.getUsername() + ":" + "sgin";
        try {
            sysSign = signMapper.selectByUserId(id);
        } catch (Exception e) {
            log.error("SignServiceImpl的findByUserId方法出错" + e.getMessage());
        }
        Date today = new Date();
        try {
            sysSign.setTodayWeek(DateUtils.dayForWeek(today));
        } catch (Throwable throwable) {
            log.error("SignServiceImpl的findByUserId方法出错");
        }
        if (userInfo.getVip().equals("1")) {
            String yesterday = null;
            try {
                yesterday = DateUtils.dayForWeek(today);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            Calendar instance = Calendar.getInstance();
            instance.setTime(today);
            instance.add(Calendar.DATE, -1);
            try {
                sysSign.setYesterday(DateUtils.dayForWeek(instance.getTime()));
            } catch (Throwable throwable) {
                log.error("SignServiceImpl的findByUserId方法出错");
            }
        }
        if (jedis.exists(key)) {
            sysSign.setCountSign(jedis.get(key));
        } else {
            sysSign.setCountSign("0");
        }
        return sysSign;
    }

    @Override
    public String signAture(SysSign sysSign) {
        UserInfo userInfo = userService.findById(sysSign.getUserId());
        String key = userInfo.getUsername() + ":" + "sgin";
        Jedis jedis = redisUtil.getJedis();
        if (userInfo.getVip().equals("1")) {
            if (jedis.exists(key)) {
                jedis.incrBy(key, 1);
            }
            signService.updateById(sysSign);
        } else {
            return "不是vip，不能补签";
        }
        return "success";
    }

    @Override
    public String sgin(String userId) {
        UserInfo userInfo = null;
        String todayWeek = null;
        SysSign sysSign = signMapper.selectByUserId(userId);
        try {
            userInfo = userService.findById(userId);
        } catch (Exception e) {
            log.error("签到通过userId查询出现错误" + e.getMessage());
            return "fail";
        }
        Jedis jedis = redisUtil.getJedis();
        String key = userInfo.getUsername() + ":" + "sgin";
        if (jedis.exists(key)) {
            jedis.incrBy(key, 1);
        } else {
            jedis.set(key, "1");
//            SysSign sysSign = new SysSign();
//            sysSign.setCountSign(count + "");
//            try {
//                signMapper.updateById(sysSign);
//            } catch (Exception e) {
//                log.error("更新次数出现问题" + e.getMessage());
//                return "fail";
//            }
        }
        Date today = new Date();
        if (sysSign == null) {
            SysSign sysSign1 = new SysSign();
            sysSign1.setUserId(userId);
            try {
                String weekStr = getWeek(sysSign1, today);
                if (weekStr.equals("fail")) {
                    return weekStr;
                }
            } catch (Throwable throwable) {
                log.error("查询第几周失败");
                return "fail";
            }
            try {
                signMapper.insertInfo(sysSign1);
                return "success";
            } catch (Exception e) {
                log.error("首次签到数据插入失败" + e.getMessage());
                return "fail";
            }
        } else {
            SysSign sysSign1 = new SysSign();
//            sysSign1.setCountSign(count + "");
            try {
                getWeek(sysSign1, today);
            } catch (Throwable throwable) {
                log.error("查询今天周几出现错误" + throwable.getMessage());
                return "fail";
            }
            sysSign1.setUserId(userId);
            try {
                signMapper.updateById(sysSign1);
            } catch (Exception e) {
                log.error("更新周几出现错误" + e.getMessage());
                return "fail";
            }
            return "success";
        }
    }

    public String getWeek(SysSign sysSign, Date today) throws Throwable {
        String todayWeek = DateUtils.dayForWeek(today);
        switch (todayWeek) {
            case "sunday":
                sysSign.setSunday("1");
                return "sunday";
            case "monday":
                sysSign.setMonday("1");
                return "monday";
            case "tuesday":
                sysSign.setTuesday("1");
                return "tuesday";
            case "wednesday":
                sysSign.setWednesday("1");
                return "wednesday";
            case "thursday":
                sysSign.setThursday("1");
                return "thursday";
            case "friday":
                sysSign.setFriday("1");
                return "friday";
            case "saturday":
                sysSign.setSaturday("1");
                return "saturday";
            default:
                return "fail";
        }
    }

    /**
     * @param
     * @return void
     * @Author Admin
     * @Description 每周更新一次
     * @Date 22:19 2020/5/5
     **/
    @Scheduled(cron = "0 0 0 */7 * ?")
//    @Scheduled(cron = "* * * * * ?")
    public void updateOfWeek() {
        log.debug("定时任务执行");
        List<SysSign> sysSignList;
        try {
            sysSignList = signService.findAll(null, null);
        } catch (Exception e) {
            log.error("定时任务查询全部用户出错" + e.getMessage());
            return;
        }
        SysSign sysSign = null;
        Jedis jedis = redisUtil.getJedis();
        for (SysSign sign : sysSignList) {
            UserInfo userInfo = userService.findById(sign.getUserId());
            String key = userInfo.getUsername() + ":" + "sgin";
            String count = null;
            if (jedis.exists(key)) {
                count = jedis.get(key);
            }
            Integer integral = userInfo.getIntegral();
            String vip = userInfo.getVip();
            List<UserInfo> fanList = userService.findFan(userInfo.getId());
            if (fanList.size() > 300) {
                integral += 3;
            } else if (fanList.size() > 700) {
                integral += 5;
            }
            if (count.equals("1")) {
                //一周首次签到给1分
                integral++;
            } else if (count.equals("3")) {
                //累计签到三次。额外赠送3积分
                integral += 4;
            } else if (count.equals("7")) {
                //累计签到7次，额外赠送4积分
                integral += 5;
            }
            if (vip.equals("1")) {
                integral += 10;
            }
            UserInfo userInfo1 = new UserInfo();
            userInfo1.setId(userInfo.getId());
            userInfo1.setIntegral(integral);
            try {
                userService.updateById(userInfo1);
            } catch (Exception e) {
                log.error("定时任务出错" + e.getMessage());
                return;
            }
            if (jedis.exists(key)) {
                jedis.del(key);
            }
        }
        jedis.close();
        try {
            signMapper.truncate();
        } catch (Exception e) {
            log.error("清空表出现错误" + e.getMessage());
            return;
        }
        log.debug("定时任务更新完成");
    }

    @Override
    public String count() {
        try {
            return signMapper.count();
        } catch (Exception e) {
            log.error("SignServiceImpl的count出错" + e.getMessage());
            return "fail";
        }
    }
}