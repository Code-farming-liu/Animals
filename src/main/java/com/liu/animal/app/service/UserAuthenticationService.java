package com.liu.animal.app.service;

import com.liu.animal.app.entity.UserAuthentication;

public interface UserAuthenticationService extends ServiceParent<UserAuthentication> {
    public String faceMatch(UserAuthentication userAuthentication);

    public String authentication(UserAuthentication userAuthentication);
}
