package com.educationalmanage.controller;

import com.educationalmanage.controller.common.Code;
import com.educationalmanage.controller.common.Result;
import com.educationalmanage.domain.*;
import com.educationalmanage.domain.Class;
import com.educationalmanage.domain.vo.CourseTableVO;
import com.educationalmanage.domain.vo.ScoreAdVO;
import com.educationalmanage.domain.vo.StudentVO;
import com.educationalmanage.domain.vo.TeacherVO;
import com.educationalmanage.service.ClassService;
import com.educationalmanage.service.CourseService;
import com.educationalmanage.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("util")
public class UtilController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ClassService classService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/department")
    public Result getDepartment(){
        List<Department> department = departmentService.getDepartment();
        Integer code = department != null ? Code.GET_OK : Code.GET_ERR;
        String msg = department != null ? "查询成功" : "查询失败，请重试";
        return new Result(code,department,msg);
    }

    @GetMapping("/class")
    public Result getClas(){
        List<Class> clas = classService.getClas();
        Integer code = clas != null ? Code.GET_OK : Code.GET_ERR;
        String msg = clas != null ? "查询成功" : "查询失败，请重试";
        return new Result(code,clas,msg);
    }

    @GetMapping("/tea/course")
    public Result getCourseByTeacherId(HttpServletRequest request){
        Integer teacherid = (Integer) request.getSession().getAttribute("userid");
        List<Course> courseList = courseService.getByTeacherid(teacherid);
        Integer code = courseList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = courseList != null ? "查询成功" : "查询失败，请重试";
        return new Result(code,courseList,msg);
    }

    @GetMapping("/course")
    public Result getCourse(){
        List<Course> course = courseService.getCourse();
        Integer code = course != null ? Code.GET_OK : Code.GET_ERR;
        String msg = course != null ? "查询成功" : "查询失败，请重试";
        return new Result(code,course,msg);
    }
}
