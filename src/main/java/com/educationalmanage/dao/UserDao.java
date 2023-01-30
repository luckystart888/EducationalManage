package com.educationalmanage.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.educationalmanage.domain.User;
import com.educationalmanage.domain.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao extends BaseMapper<User> {

    @Select("select id,login.userid,name from login,student ${ew.getCustomSqlSegment()}")
    UserVO getStuUserVO(@Param("ew") Wrapper wrapper);

    @Select("select id,login.userid,name from login,teacher ${ew.getCustomSqlSegment()}")
    UserVO getTeaUserVO(@Param("ew") Wrapper wrapper);
}
