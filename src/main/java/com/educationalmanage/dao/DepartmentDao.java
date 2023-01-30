package com.educationalmanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.educationalmanage.domain.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DepartmentDao extends BaseMapper<Department> {

    @Select("select id from department where departmentname = #{departmentName}")
    Integer selectIdByName(String departmentName);
}
