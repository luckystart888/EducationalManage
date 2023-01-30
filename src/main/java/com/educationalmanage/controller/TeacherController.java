package com.educationalmanage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.educationalmanage.controller.common.Code;
import com.educationalmanage.controller.common.Result;
import com.educationalmanage.controller.common.VoUtil;
import com.educationalmanage.domain.Score;
import com.educationalmanage.domain.vo.ScoreTeaVO;
import com.educationalmanage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private GraduateService graduateService;

    @GetMapping("/score/{currentPage}/{pageSize}")
    public Result getScorePage (HttpServletRequest request,@PathVariable int currentPage, @PathVariable int pageSize, ScoreTeaVO scoreTeaVO){
        Integer userid = (Integer) request.getSession().getAttribute("userid");
        IPage<ScoreTeaVO> page = scoreService.getTeaPage(currentPage,pageSize,scoreTeaVO,userid);
        if( currentPage > page.getPages()){
            page = scoreService.getTeaPage(currentPage,pageSize,scoreTeaVO,userid);
        }
        Integer code = page != null ? Code.GET_OK : Code.GET_ERR;
        String msg = page != null ? "查询成功" : "查询失败，请重试";
        return new Result(code,page,msg);
    }

    @PutMapping("/score")
    @Transactional
    public Result updateScore(@RequestBody ScoreTeaVO scoreTeaVO){
        Score score = VoUtil.getToScore(scoreTeaVO);
        System.out.println(score);
        if (score.getScore() != null && score.getScore() >= 60){
            score.setCredit(courseService.getCredit(score.getCourseid()));
        }else{
            score.setCredit(0.0);
        }
        Boolean flag = scoreService.updateScore(score);
        if (flag){
            graduateService.totalCredit(score.getStudentid());
        }
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR,flag);
    }

    @GetMapping("/score/{scoreid}")
    public Result getScoreTeaVoById(@PathVariable Integer scoreid){
        ScoreTeaVO scoreTeaVo = scoreService.getScoreTeaVoById(scoreid);
        Integer code = scoreTeaVo != null ? Code.GET_OK : Code.GET_ERR;
        String msg = scoreTeaVo != null ? "查询成功" : "查询失败，请重试";
        return new Result(code,scoreTeaVo,msg);
    }

}
