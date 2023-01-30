package com.educationalmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.educationalmanage.domain.Course;
import com.educationalmanage.domain.Score;
import com.educationalmanage.domain.vo.ScoreAdVO;
import com.educationalmanage.domain.vo.ScoreStuVO;
import com.educationalmanage.domain.vo.ScoreTeaVO;

import java.util.List;


public interface ScoreService {

    IPage<ScoreStuVO> getStuPage(int currentPage, int pageSize, Score score);

    IPage<ScoreTeaVO> getTeaPage(int currentPage, int pageSize, ScoreTeaVO scoreTeaVO, Integer userid);

    IPage<ScoreAdVO> getAdPage(int currentPage, int pageSize, ScoreAdVO scoreAdVO);

    ScoreTeaVO getScoreTeaVoById(Integer scoreid);

    ScoreAdVO getScoreAdVoById(Integer scoreid);

    Boolean updateScore(Score score);

    Boolean addScore(Score score);

    Boolean deleteScore(Integer id);

    Boolean deleteByCourseId(Integer courseid);
}
