package com.educationalmanage.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.educationalmanage.domain.Score;
import com.educationalmanage.domain.vo.ScoreAdVO;
import com.educationalmanage.domain.vo.ScoreStuVO;
import com.educationalmanage.domain.vo.ScoreTeaVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScoreDao extends BaseMapper<Score> {

    @Select("select score.id,course.coursename,course.teacherid,teacher.name,score.score,score.credit " +
            "from course,score,teacher ${ew.getCustomSqlSegment()}")
    IPage<ScoreStuVO> getStuPage(IPage<ScoreStuVO> page, @Param("ew") Wrapper wrapper);

    @Select("select score.id,course.courseid,course.coursename,studentid,student.name,class.classname,score.score,score.credit " +
            "from course,score,student,class ${ew.getCustomSqlSegment()}")
    IPage<ScoreTeaVO> getTeaPage(IPage<ScoreTeaVO> page, @Param("ew") Wrapper wrapper);

    @Select("select score.id,course.courseid,course.coursename,teacherid,teacher.name,studentid,student.name as name1,class.classname,score.score,score.credit " +
            "from course,score,student,teacher,class ${ew.getCustomSqlSegment()}")
    IPage<ScoreAdVO> getAdPage(IPage<ScoreTeaVO> page, @Param("ew") Wrapper wrapper);

    @Select("select id from score where courseid = #{courseid}")
    List<Integer> getId(Integer courseid);

    @Select("select score.id,course.courseid,course.coursename,studentid,student.name,class.classname,score.score,score.credit " +
            "from course,score,student,class ${ew.getCustomSqlSegment()}")
    ScoreTeaVO getScoreTeaVoById(Integer scoreid,@Param("ew") Wrapper wrapper);

    @Select("select score.id,course.courseid,course.coursename,teacherid,teacher.name,studentid,student.name as name1,class.classname,score.score,score.credit " +
            "from course,score,student,teacher,class ${ew.getCustomSqlSegment()}")
    ScoreAdVO getScoreAdVoById(Integer scoreid,@Param("ew") Wrapper wrapper);
}
