package com.educationalmanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.educationalmanage.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginDao extends BaseMapper<User> {
}
