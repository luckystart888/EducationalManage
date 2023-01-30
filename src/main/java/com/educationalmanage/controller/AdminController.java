package com.educationalmanage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.educationalmanage.controller.common.Code;
import com.educationalmanage.controller.common.VoUtil;
import com.educationalmanage.controller.common.Result;
import com.educationalmanage.domain.*;
import com.educationalmanage.domain.vo.EmploymentVO;
import com.educationalmanage.domain.vo.GraduateVO;
import com.educationalmanage.domain.vo.ScoreAdVO;
import com.educationalmanage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private GraduateService graduateService;

    @Autowired
    private EmploymentService employmentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ScoreService scoreService;


    @GetMapping("/score/{currentPage}/{pageSize}")
    public Result getScorePage (@PathVariable int currentPage, @PathVariable int pageSize, ScoreAdVO scoreAdVO){
        IPage<ScoreAdVO> page = scoreService.getAdPage(currentPage,pageSize,scoreAdVO);
        if( currentPage > page.getPages()){
            page = scoreService.getAdPage(currentPage,pageSize,scoreAdVO);
        }
        Integer code = page != null ? Code.GET_OK : Code.GET_ERR;
        String msg = page != null ? "查询成功" : "查询失败，请重试";
        return new Result(code,page,msg);
    }

    @PutMapping("/score")
    @Transactional
    public Result updateScore(@RequestBody ScoreAdVO scoreAdVO){
        Score score = VoUtil.getToScore(scoreAdVO);
        System.out.println(score);
        if (score.getScore() != null && score.getScore() >= 60){
            score.setCredit(courseService.getCredit(score.getCourseid()));
        }else{
            score.setCredit(0.0);
            System.out.println(score.getScore());
        }
        Boolean flag = scoreService.updateScore(score);
        if (flag){
            graduateService.totalCredit(score.getStudentid());
        }
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR,flag);
    }

    @GetMapping("/score/{scoreid}")
    public Result getScoreAdVoById(@PathVariable Integer scoreid){
        ScoreAdVO scoreAdVo = scoreService.getScoreAdVoById(scoreid);
        Integer code = scoreAdVo != null ? Code.GET_OK : Code.GET_ERR;
        String msg = scoreAdVo != null ? "查询成功" : "查询失败，请重试";
        return new Result(code,scoreAdVo,msg);
    }


    @GetMapping("/graduate/{currentPage}/{pageSize}")
    public Result getGraduatePage(@PathVariable int currentPage, @PathVariable int pageSize,GraduateVO graduateVO) {
        IPage<GraduateVO> page = graduateService.getGraduate(currentPage, pageSize,graduateVO);
        if( currentPage > page.getPages()){
            page = graduateService.getGraduate((int)page.getPages(), pageSize,graduateVO);
        }
        Integer code = page != null ? Code.GET_OK : Code.GET_ERR;
        String msg = page != null ? "查询成功" : "查询失败，请重试";
        return new Result(code,page,msg);
    }

    @GetMapping("/graduate/{studentid}")
    public Result getGraduate(@PathVariable Integer studentid){
        GraduateVO graduateVO = graduateService.selectByUserid(studentid);
        Integer code = graduateVO != null ? Code.GET_OK : Code.GET_ERR;
        String msg = graduateVO != null ? "查询成功" : "查询失败，请重试";
        return new Result(code,graduateVO,msg);
    }

    @PutMapping("/graduate")
    public Result updateGraduate(@RequestBody GraduateVO graduateVO){
        Graduate graduate = new Graduate();
        graduate.setStudentid(graduateVO.getStudentid());
        graduate.setStatus(graduateVO.getStatus());
        Boolean flag = graduateService.updateGraduate(graduate);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR,flag);
    }

    @GetMapping("/employment/{currentPage}/{pageSize}")
    public Result getEmploymentPage(@PathVariable int currentPage, @PathVariable int pageSize, EmploymentVO employmentVO) {
        IPage<EmploymentVO> page = employmentService.getEmploymentPage(currentPage, pageSize,employmentVO);
        if( currentPage > page.getPages()){
            page = employmentService.getEmploymentPage((int)page.getPages(), pageSize,employmentVO);
        }
        Integer code = page != null ? Code.GET_OK : Code.GET_ERR;
        String msg = page != null ? "查询成功" : "查询失败，请重试";
        return new Result(code,page,msg);
    }
}
