package com.liu.animal.app.service;

import com.liu.animal.app.entity.SysSign;

public interface SignService extends ServiceParent<SysSign>{
    public String sgin(String userId);
    public SysSign findByUserId(String userId);

    String signAture(SysSign sysSign);
}
