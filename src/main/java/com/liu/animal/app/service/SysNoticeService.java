package com.liu.animal.app.service;

import com.liu.animal.app.entity.SysNotice;

import java.util.List;


public interface SysNoticeService extends ServiceParent<SysNotice>{
    List<SysNotice> findByUserId(String userId);

    SysNotice findEndNotice();
}
