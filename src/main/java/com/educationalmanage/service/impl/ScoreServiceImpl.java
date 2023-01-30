package com.educationalmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educationalmanage.dao.ScoreDao;
import com.educationalmanage.domain.Score;
import com.educationalmanage.domain.vo.ScoreAdVO;
import com.educationalmanage.domain.vo.ScoreStuVO;
import com.educationalmanage.domain.vo.ScoreTeaVO;
import com.educationalmanage.service.ScoreService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreDao scoreDao;

    @Override
    public IPage<ScoreStuVO> getStuPage(int currentPage, int pageSize, Score score) {
        QueryWrapper<ScoreTeaVO> qw = new QueryWrapper<>();
        qw.apply("course.courseid = score.courseid");
        qw.apply("course.teacherid = teacher.userid");
        qw.eq("score.studentid",score.getStudentid());
        IPage<ScoreStuVO> page = new Page<>(currentPage,pageSize);
        return scoreDao.getStuPage(page,qw);
    }

    @Override
    public IPage<ScoreTeaVO> getTeaPage(int currentPage, int pageSize, ScoreTeaVO scoreTeaVO, Integer userid) {
        QueryWrapper<ScoreTeaVO> qw = new QueryWrapper<>();
        qw.apply("score.courseid = course.courseid");
        qw.apply("student.userid = studentid");
        qw.apply("student.classid = class.id");
        qw.like(Strings.isNotEmpty(scoreTeaVO.getName()),"student.name",scoreTeaVO.getName());
        qw.eq(Strings.isNotEmpty(scoreTeaVO.getCourseName()),"course.coursename",scoreTeaVO.getCourseName());
        qw.eq(Strings.isNotEmpty(scoreTeaVO.getClassname()),"class.classname",scoreTeaVO.getClassname());
        qw.eq("course.teacherid",userid);
        IPage<ScoreTeaVO> page = new Page<>(currentPage,pageSize);
        return scoreDao.getTeaPage(page,qw);
    }

    @Override
    public IPage<ScoreAdVO> getAdPage(int currentPage, int pageSize, ScoreAdVO scoreAdVO) {
        QueryWrapper<ScoreTeaVO> qw = new QueryWrapper<>();
        qw.apply("score.courseid = course.courseid");
        qw.apply("student.userid = studentid");
        qw.apply("student.classid = class.id");
        qw.apply("teacherid = teacher.userid");
        qw.like(Strings.isNotEmpty(scoreAdVO.getName()),"teacher.name",scoreAdVO.getName());
        qw.like(Strings.isNotEmpty(scoreAdVO.getName1()),"student.name",scoreAdVO.getName1());
        qw.eq(Strings.isNotEmpty(scoreAdVO.getCourseName()),"course.coursename",scoreAdVO.getCourseName());
        qw.eq(Strings.isNotEmpty(scoreAdVO.getClassname()),"class.classname",scoreAdVO.getClassname());
        IPage<ScoreTeaVO> page = new Page<>(currentPage,pageSize);
        return scoreDao.getAdPage(page,qw);
    }

    @Override
    public ScoreTeaVO getScoreTeaVoById(Integer scoreid) {
        QueryWrapper<ScoreTeaVO> qw = new QueryWrapper<>();
        qw.apply("score.courseid = course.courseid");
        qw.apply("student.userid = studentid");
        qw.apply("student.classid = class.id");
        qw.eq("score.id",scoreid);
        return scoreDao.getScoreTeaVoById(scoreid,qw);
    }

    @Override
    public ScoreAdVO getScoreAdVoById(Integer scoreid) {
        QueryWrapper<ScoreTeaVO> qw = new QueryWrapper<>();
        qw.apply("score.courseid = course.courseid");
        qw.apply("student.userid = studentid");
        qw.apply("student.classid = class.id");
        qw.apply("teacherid = teacher.userid");
        qw.eq("score.id",scoreid);
        return scoreDao.getScoreAdVoById(scoreid,qw);
    }

    @Override
    public Boolean updateScore(Score score) {
        return scoreDao.updateById(score) > 0;
    }

    @Override
    public Boolean addScore(Score score) {
        return scoreDao.insert(score) > 0;
    }

    @Override
    public Boolean deleteScore(Integer userid) {
        LambdaQueryWrapper<Score> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Score::getStudentid,userid);
        return scoreDao.delete(lqw) > 0;
    }

    @Override
    public Boolean deleteByCourseId(Integer courseid) {
        LambdaQueryWrapper<Score> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Score::getCourseid,courseid);
        return scoreDao.delete(lqw) > 0;
    }

}
