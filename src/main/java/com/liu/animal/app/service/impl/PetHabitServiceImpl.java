package com.liu.animal.app.service.impl;

import com.liu.animal.app.entity.PetHabit;
import com.liu.animal.app.mapper.PetHabitMapper;
import com.liu.animal.app.service.PetHabitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: CommenServiceImpl
 * @Description: TODO
 * @Author: Admin
 * @Date 2020/5/6 17:35
 **/
@Service
@Slf4j
@Transactional
public class PetHabitServiceImpl implements PetHabitService {
    @Autowired
    private PetHabitMapper petHabitMapper;

    @Override
    public List<PetHabit> findAll(Integer page, Integer num) {
        List<PetHabit> petHabits = null;
        try {
            if (page == null && num == null) {
                page = 0;
                num = Integer.parseInt(petHabitMapper.count());
            } else {
                page = (page - 1) * num;
            }
            petHabits = petHabitMapper.selectAll(page, num);
        } catch (Exception e) {
            log.error("PetHabitServiceImpl的findAll方法出错" + e.getMessage());
            return null;
        }
        return petHabits;
    }

    @Override
    public int addInfo(PetHabit petHabit) {
        try {
            return petHabitMapper.insertInfo(petHabit);
        } catch (Exception e) {
            log.error("PetHabitServiceImpl的addInfo方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int updateById(PetHabit petHabit) {
        try {
            return petHabitMapper.updateById(petHabit);
        } catch (Exception e) {
            log.error("PetHabitServiceImpl的updateById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteById(String id) {
        try {
            return petHabitMapper.deleteById(id);
        } catch (Exception e) {
            log.error("PetHabitServiceImpl的deleteById方法出错" + e.getMessage());
            return 0;
        }
    }

    @Override
    public PetHabit findById(String id) {
        PetHabit petHabit;
        try {
            petHabit = petHabitMapper.selectById(id);
        } catch (Exception e) {
            log.error("PetHabitServiceImpl的findById方法出错" + e.getMessage());
            return null;
        }
        return petHabit;
    }

    @Override
    public String count() {
        try {
            return petHabitMapper.count();
        } catch (Exception e) {
            log.error("PetHabitServiceImpl的count出错" + e.getMessage());
            return "fail";
        }
    }

    @Override
    public List<PetHabit> findByPetInfoId(String id) {
        try {
            return petHabitMapper.selectByPetInfoId(id);
        } catch (Exception e) {
            log.error("PetHabitServiceImpl的findByPetInfoId出错" + e.getMessage());
            return null;
        }
    }
}