package com.educationalmanage.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.educationalmanage.domain.Graduate;
import com.educationalmanage.domain.vo.GraduateVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface GraduateDao extends BaseMapper<Graduate> {
    @Select("select studentid,student.name,class.classname,credit1,credit2,status FROM graduate,student,class ${ew.getCustomSqlSegment()}")
    IPage<GraduateVO> getGraduatePage(IPage<GraduateVO> page, @Param("ew") Wrapper wrapper);

    @Select("select studentid,student.name,class.classname,credit1,credit2,status FROM graduate,student,class ${ew.getCustomSqlSegment()}")
    GraduateVO selectGraduateByUserid(@Param("ew") Wrapper wrapper);

    @Update("update graduate SET credit2 = (SELECT SUM(credit) FROM score WHERE studentid = #{student}) WHERE studentid = #{student}")
    Integer totalCredit(Integer studentid);
}
