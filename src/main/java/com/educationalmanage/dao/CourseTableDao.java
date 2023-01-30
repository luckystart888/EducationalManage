package com.educationalmanage.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.educationalmanage.domain.CourseTable;
import com.educationalmanage.domain.vo.CourseTableVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CourseTableDao extends BaseMapper<CourseTable> {

    @Select("select coursetable.id,course.courseid,coursename,teacher.name,coursetime,classroom,courseweek,coursetype,class.classname,credit from course,coursetable,student,teacher,class ${ew.getCustomSqlSegment()}")
    IPage<CourseTableVO> getStuCourseTablePage(IPage<CourseTableVO> page, @Param("ew") Wrapper wrapper);

    @Select("select coursetable.id,course.courseid,coursename,teacher.name,coursetime,classroom,courseweek,coursetype,class.classname,credit from course,coursetable,teacher,class ${ew.getCustomSqlSegment()}")
    IPage<CourseTableVO> getTeaCourseTablePage(IPage<CourseTableVO> page, @Param("ew") Wrapper wrapper);

    @Select("select coursetable.id,course.courseid,coursename,teacher.name,coursetime,classroom,courseweek,coursetype,class.classname,credit from course,coursetable,teacher,class ${ew.getCustomSqlSegment()}")
    IPage<CourseTableVO> getAdCourseTablePage(IPage<CourseTableVO> page, @Param("ew") Wrapper wrapper);

    @Select("select coursetable.id,course.courseid,coursename,teacher.name,coursetime,classroom,courseweek,coursetype,class.classname,credit from course,coursetable,teacher,class ${ew.getCustomSqlSegment()}")
    CourseTableVO getCourseTableVOById(@Param("ew") Wrapper wrapper);
}
