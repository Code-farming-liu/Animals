package com.liu.animal.app.service;

import com.liu.animal.app.entity.SysCountOfDay;

import java.util.List;

public interface SysCountOfDayService extends ServiceParent<SysCountOfDay> {
    List<SysCountOfDay> findByUserId(String createUserId);
}
