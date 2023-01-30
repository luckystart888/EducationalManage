package com.educationalmanage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.educationalmanage.controller.common.Code;
import com.educationalmanage.controller.common.Result;
import com.educationalmanage.controller.common.VoUtil;
import com.educationalmanage.domain.*;
import com.educationalmanage.domain.vo.*;
import com.educationalmanage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private CourseTableService courseTableService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private GraduateService graduateService;

    @Autowired
    private EmploymentService employmentService;

    @GetMapping("/user/info/{userid}")
    public Result getStudentById(@PathVariable Integer userid){
        StudentVO studentVO = studentInfoService.getStudentVOById(userid);
        Integer code = studentVO != null ? Code.GET_OK : Code.GET_ERR;
        String msg = studentVO != null ? "查询成功" : "查询失败，请重试";
        return new Result(code,studentVO,msg);
    }

    @GetMapping("/courseTable/{currentPage}/{pageSize}")
    public Result getCourseTablePage (HttpServletRequest request, @PathVariable int currentPage, @PathVariable int pageSize) {
        Integer userid = (Integer) request.getSession().getAttribute("userid");
        IPage<CourseTableVO> coursePage = courseTableService.getStuCourseTablePage(currentPage, pageSize, userid);
        if( currentPage > coursePage.getPages()){
            coursePage = courseTableService.getStuCourseTablePage((int)coursePage.getPages(), pageSize, userid);
        }
        Integer code = coursePage != null ? Code.GET_OK : Code.GET_ERR;
        String msg = coursePage != null ? "查询成功" : "查询失败，请重试";
        return new Result(code,coursePage,msg);
    }

    @GetMapping("/score/{currentPage}/{pageSize}")
    public Result getScorePage (HttpServletRequest request, @PathVariable int currentPage, @PathVariable int pageSize, Score score){
        Integer userid = (Integer) request.getSession().getAttribute("userid");
        score.setStudentid(userid);
        IPage<ScoreStuVO> page = scoreService.getStuPage(currentPage,pageSize,score);
        if( currentPage > page.getPages()){
            page = scoreService.getStuPage(currentPage,pageSize,score);
        }
        Integer code = page != null ? Code.GET_OK : Code.GET_ERR;
        String msg = page != null ? "查询成功" : "查询失败，请重试";
        return new Result(code,page,msg);
    }

    @GetMapping("/graduate")
    public Result selectGraduateByUserid(HttpServletRequest request){
        Integer userid = (Integer) request.getSession().getAttribute("userid");
        GraduateVO graduate = graduateService.selectByUserid(userid);
        Integer code = graduate != null ? Code.GET_OK : Code.GET_ERR;
        return new Result(code,graduate);
    }

    @GetMapping("/employment")
    public Result getEmploymentByUserid(HttpServletRequest request){
        Integer userid = (Integer) request.getSession().getAttribute("userid");
        EmploymentVO employmentVO = employmentService.getEmployment(userid);
        Integer code = employmentVO != null ? Code.GET_OK : Code.GET_ERR;
        return new Result(code,employmentVO);
    }

    @PutMapping("/employment")
    public Result updateEmployment(@RequestBody EmploymentVO employmentVO){
        System.out.println(employmentVO);
        Employment employment = VoUtil.getToEmployment(employmentVO);
        System.out.println(employment);
        Boolean flag = employmentService.updateEmployment(employment);
        Integer code = flag ? Code.UPDATE_OK : Code.UPDATE_ERR;
        String msg = flag ? "修改成功" : "修改失败";
        return new Result(code,employment,msg);
    }


}
