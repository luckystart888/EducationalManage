package com.educationalmanage.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.educationalmanage.domain.Teacher;
import com.educationalmanage.domain.vo.TeacherVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeacherInfoDao extends BaseMapper<Teacher> {

    @Select("select userid,teacher.name,gender,tel,departmentname,title from teacher,department ${ew.getCustomSqlSegment()}")
    IPage<TeacherVO> getTeaPage(IPage<TeacherVO> page, @Param("ew") Wrapper wrapper);
    @Select("select userid,teacher.name,gender,tel,departmentname,title from teacher,department ${ew.getCustomSqlSegment()}")
    TeacherVO getTeacherById(Integer userid, @Param("ew") Wrapper wrapper);
}
