package com.educationalmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.educationalmanage.dao.LoginDao;
import com.educationalmanage.domain.User;
import com.educationalmanage.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Override
    public User login(Integer userid, String password) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserid,userid);
        User login = loginDao.selectOne(lqw);
        if (login != null){
            return login.getPassword().equals(password) ? login : null;
        }else {
            return null;
        }
    }

    @Override
    public Integer selectUserId(Integer userid) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.select(User::getUserid).eq(User::getUserid,userid);
        return loginDao.selectCount(lqw);
    }

    @Override
    public Boolean updatePasswd(Integer userid, String password) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserid,userid);
        User user = new User();
        user.setPassword(password);
        return loginDao.update(user,lqw) > 0;
    }
}
