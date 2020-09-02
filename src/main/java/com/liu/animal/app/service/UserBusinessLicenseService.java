package com.liu.animal.app.service;

import com.liu.animal.app.entity.UserBusinessLicense;

public interface UserBusinessLicenseService extends ServiceParent<UserBusinessLicense>{
    public String businessLicense(UserBusinessLicense userBusinessLicense);
}
