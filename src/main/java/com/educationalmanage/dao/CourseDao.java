package com.educationalmanage.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.educationalmanage.domain.Course;
import com.educationalmanage.domain.vo.CourseVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseDao extends BaseMapper<Course> {

    @Select("select courseid,coursename,teacher.name,coursetype,courseweek,class.classname,credit from course,teacher,class ${ew.getCustomSqlSegment()}")
    IPage<CourseVO> getTeaCourseVOPage(IPage<CourseVO> page, @Param("ew") Wrapper wrapper);

    @Select("select courseid,coursename,teacher.name,coursetype,courseweek,class.classname,credit from course,teacher,class ${ew.getCustomSqlSegment()}")
    IPage<CourseVO> getAdCourseVOPage(IPage<CourseVO> page, @Param("ew") Wrapper wrapper);

    @Select("select courseid,coursename,teacher.name,coursetype,courseweek,class.classname,credit from course,teacher,class ${ew.getCustomSqlSegment()}")
    CourseVO getCourseVOById(@Param("ew") Wrapper wrapper);

    @Select("select courseid from course where classid = #{classid}")
    List<Integer> getCourseid(Integer classid);

    @Select("select courseid from course where teacherid = #{teacherid}")
    List<Integer> selectIdByTeacherid(Integer teacherid);

    @Select("select * from course where teacherid = #{teacherid}")
    List<Course> getByTeacherid(Integer teacherid);

    @Select("select distinct coursename from course")
    List<Course> getCourse();
}
