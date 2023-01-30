package com.educationalmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.educationalmanage.dao.UserDao;
import com.educationalmanage.domain.User;
import com.educationalmanage.domain.vo.UserVO;
import com.educationalmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao UserDao;

    private static String defaultPassword = "123456";

    @Override
    public Boolean addUser(User user) {
        return UserDao.insert(user) > 0;
    }

    @Override
    public Boolean deleteUser(Integer userid) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserid,userid);
        return UserDao.delete(lqw) > 0;
    }

    @Override
    public Boolean resetPassword(User user) {
        user.setPassword(defaultPassword);
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserid,user.getUserid());
        return UserDao.update(user,lqw) > 0;
    }

    @Override
    public Integer selectUserId(Integer userid) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.select(User::getUserid).eq(User::getUserid,userid);
        return UserDao.selectCount(lqw);
    }

    @Override
    public User getByUserid(Integer userid) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserid,userid);
        return UserDao.selectOne(lqw);
    }

    @Override
    public UserVO getStuUserVO(Integer userid) {
        QueryWrapper<UserVO> qw = new QueryWrapper<>();
        qw.apply("login.userid=student.userid");
        qw.eq("login.userid",userid);
        return UserDao.getStuUserVO(qw);
    }

    @Override
    public UserVO getTeaUserVO(Integer userid) {
        QueryWrapper<UserVO> qw = new QueryWrapper<>();
        qw.apply("login.userid=teacher.userid");
        qw.eq("login.userid",userid);
        return UserDao.getTeaUserVO(qw);
    }
}
