package com.educationalmanage.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.educationalmanage.domain.Employment;
import com.educationalmanage.domain.vo.EmploymentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmploymentDao extends BaseMapper<Employment> {

    @Select("select studentid,student.name,class.classname,workprovince,workunit,wages from student,employment,class ${ew.getCustomSqlSegment()}")
    IPage<EmploymentVO> getEmploymentPage(IPage<EmploymentVO> page, @Param("ew") Wrapper wrapper);

    @Select("select studentid,student.name,class.classname,workprovince,workunit,wages from student,employment,class ${ew.getCustomSqlSegment()}")
    EmploymentVO getEmployment(@Param("ew") Wrapper wrapper);
}
