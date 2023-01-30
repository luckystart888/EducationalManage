package com.educationalmanage.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.educationalmanage.domain.Student;
import com.educationalmanage.domain.vo.StudentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentInfoDao extends BaseMapper<Student> {

    //mybatis plus多表查询 使用QueryWrapper的getCustomSqlSegment()方法获取QueryWrapper的SQL
    @Select("select userid,student.name,gender,birthday,indate,tel,class.classname from student,class ${ew.getCustomSqlSegment()}")
    IPage<StudentVO> getStuPage(IPage<StudentVO> page, @Param("ew") Wrapper wrapper);

    @Select("select userid from student where classid = #{classid}")
    List<Integer> selectUserid(Integer classid);

    @Select("select userid,student.name,gender,birthday,indate,tel,class.classname from student,class ${ew.getCustomSqlSegment()}")
    StudentVO getStudentVOById(Integer userid, @Param("ew") Wrapper wrapper);
}
