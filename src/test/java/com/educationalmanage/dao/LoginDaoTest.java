package com.educationalmanage.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.educationalmanage.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginDaoTest {

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private UserDao adminUserDao;

    @Autowired
    private CourseDao courseDao;

    @Test
    public void getLogin(){
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserid,101);
        User login = loginDao.selectOne(lqw);
        System.out.println(login);
    }

    @Test
    public void selectId(){
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.select("userid").eq("userid",11001);
        User user = adminUserDao.selectOne(qw);
        System.out.println(adminUserDao.selectOne(qw));
        System.out.println(user);
    }

    @Test
    public void testById(){
        System.out.println(courseDao.selectById(4));
    }

}
