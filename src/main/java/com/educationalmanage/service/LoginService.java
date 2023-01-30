package com.educationalmanage.service;

import com.educationalmanage.domain.User;

public interface LoginService {

    User login(Integer userid, String password);
    Integer selectUserId(Integer userid);
    Boolean updatePasswd(Integer userid, String password);
}
