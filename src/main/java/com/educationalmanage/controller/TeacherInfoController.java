package com.educationalmanage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.educationalmanage.controller.common.Code;
import com.educationalmanage.controller.common.Result;
import com.educationalmanage.controller.common.VoUtil;
import com.educationalmanage.domain.*;
import com.educationalmanage.domain.vo.StudentVO;
import com.educationalmanage.exception.BusinessException;
import com.educationalmanage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher/info")
public class TeacherInfoController {

    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private UserService userService;

    @Autowired
    private GraduateService graduateService;

    @Autowired
    private ClassService classService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EmploymentService employmentService;

    @PostMapping("/student")
    @Transactional
    public Result addStuInfo(@RequestBody StudentVO studentVO){
        if (studentInfoService.selectByUserId(studentVO.getUserid()) != 0){
            throw new BusinessException("用户名已存在",Code.BUSINESS_ERR);
        }
        Student student = VoUtil.getToStudent(studentVO);
        Boolean flag = studentInfoService.addInfo(student);
        if (flag){
            List<Integer> courseidList = courseService.getCourseid(student.getClassid());
            Score score = new Score();
            User user = new User();
            user.setUserid(student.getUserid());
            user.setRole(3);
            Graduate graduate = new Graduate();
            graduate.setStudentid(student.getUserid());
            userService.addUser(user);
            Employment employment = new Employment();
            employment.setStudentid(student.getUserid());
            employmentService.addEmployment(employment);
            graduateService.addGraduate(graduate);
            for (Integer couresid : courseidList){
                score.setCourseid(couresid);
                score.setStudentid(student.getUserid());
                scoreService.addScore(score);
            }
        }
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR,flag);
    }

    @DeleteMapping("/student/{userid}")
    @Transactional
    public Result deleteStuInfo(@PathVariable Integer userid){
        graduateService.deleteGraduate(userid);
        employmentService.deleteEmployment(userid);
        scoreService.deleteScore(userid);
        Boolean flag = studentInfoService.deleteInfo(userid);
        if (flag){
            userService.deleteUser(userid);
        }
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR,flag);
    }

    @PutMapping("/student")
    @Transactional
    public Result updateStuInfo(@RequestBody StudentVO studentVO){
        StudentVO studentVOById = studentInfoService.getStudentVOById(studentVO.getUserid());
        Student student = VoUtil.getToStudent(studentVO);
        Score score = new Score();
        if(!studentVOById.getClassName().equals(studentVO.getClassName())){
            scoreService.deleteScore(studentVO.getUserid());
            List<Integer> courseidList = courseService.getCourseid(student.getClassid());
            for (Integer couresid : courseidList) {
                score.setCourseid(couresid);
                score.setStudentid(student.getUserid());
                scoreService.addScore(score);
            }
        }
        Boolean flag = studentInfoService.updateInfo(student);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR,flag);
    }

    @GetMapping("/student/{currentPage}/{pageSize}")
    public Result getStuPage(@PathVariable int currentPage, @PathVariable int pageSize, StudentVO studentVO){
        IPage<StudentVO> page = studentInfoService.getStuPage(currentPage, pageSize,studentVO);
        if( currentPage > page.getPages()){
            page = studentInfoService.getStuPage((int)page.getPages(), pageSize,studentVO);
        }
        Integer code = page != null ? Code.GET_OK : Code.GET_ERR;
        String msg = page != null ? "查询成功" : "查询失败，请重试";
        return new Result(code,page,msg);
    }

    @GetMapping("/student/{userid}")
    public Result getStuById(@PathVariable Integer userid){
        StudentVO studentVO = studentInfoService.getStudentVOById(userid);
        Integer code = studentVO != null ? Code.GET_OK : Code.GET_ERR;
        String msg = studentVO != null ? "查询成功" : "查询失败，请重试";
        return new Result(code,studentVO,msg);
    }
}
