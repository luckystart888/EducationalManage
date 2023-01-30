package com.educationalmanage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.educationalmanage.controller.common.Code;
import com.educationalmanage.controller.common.Result;
import com.educationalmanage.controller.common.VoUtil;
import com.educationalmanage.domain.Course;
import com.educationalmanage.domain.CourseTable;
import com.educationalmanage.domain.Score;
import com.educationalmanage.domain.vo.CourseTableVO;
import com.educationalmanage.domain.vo.CourseVO;
import com.educationalmanage.exception.BusinessException;
import com.educationalmanage.service.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
@Log4j2
public class AdminControllerCourse {

    @Autowired
    private CourseTableService courseTableService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private TeacherInfoService teacherInfoService;


    @GetMapping("/courseTable/{currentPage}/{pageSize}")
    public Result getAdCourseTablePage(@PathVariable int currentPage, @PathVariable int pageSize, CourseTableVO courseTableVO) {
        IPage<CourseTableVO> courseTablePage = courseTableService.getAdCourseTablePage(currentPage, pageSize, courseTableVO);
        if (currentPage > courseTablePage.getPages()) {
            courseTablePage = courseTableService.getAdCourseTablePage((int) courseTablePage.getPages(), pageSize, courseTableVO);
        }
        Integer code = courseTablePage != null ? Code.GET_OK : Code.GET_ERR;
        String msg = courseTablePage != null ? "查询成功" : "查询失败，请重试";
        return new Result(code, courseTablePage, msg);
    }

    @PutMapping("/courseTable")
    public Result updateCourseTable(@RequestBody CourseTableVO courseTableVO) {
        CourseTable courseTable = VoUtil.getToCourseTable(courseTableVO);
        Boolean flag = courseTableService.updateCourseTable(courseTable);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }

    @PostMapping("/courseTable")
    public Result addCourseTable(@RequestBody CourseTable courseTable) {
        Boolean flag = courseTableService.addCourseTable(courseTable);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    @DeleteMapping("/courseTable/{id}")
    public Result deleteCourseTable(@PathVariable Integer id) {
        Boolean flag = courseTableService.deleteCourseTableById(id);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }

    @GetMapping("/courseTable/{id}")
    public Result getCourseTableVOById(@PathVariable Integer id) {
        CourseTableVO courseTableVO = courseTableService.getCourseTableVOById(id);
        Integer code = courseTableVO != null ? Code.GET_OK : Code.GET_ERR;
        String msg = courseTableVO != null ? "查询成功" : "查询失败，请重试";
        return new Result(code, courseTableVO, msg);
    }


    @GetMapping("/course/{currentPage}/{pageSize}")
    public Result getAdCoursePage(@PathVariable int currentPage, @PathVariable int pageSize, CourseVO courseVO) {
        IPage<CourseVO> coursePage = courseService.getAdCourseVOPage(currentPage, pageSize, courseVO);
        System.out.println(coursePage);
        if (currentPage > coursePage.getPages()) {
            coursePage = courseService.getAdCourseVOPage((int) coursePage.getPages(), pageSize, courseVO);
        }
        Integer code = coursePage != null ? Code.GET_OK : Code.GET_ERR;
        String msg = coursePage != null ? "查询成功" : "查询失败，请重试";
        return new Result(code, coursePage, msg);
    }

    @PutMapping("/course")
    @Transactional
    public Result updateCourse(@RequestBody Course course) {
        Course courseById = courseService.getCourseById(course.getCourseid());
        Score score = new Score();
        log.info("修改前的课程班级id：" + courseById.getClassid());
        log.info("修改后的课程班级id：" + course.getClassid());
        if (courseById.getClassid() != course.getClassid()) {
            scoreService.deleteByCourseId(course.getCourseid());
            List<Integer> studentList = studentInfoService.selectUserId(course.getClassid());
            for (Integer userid : studentList) {
                score.setCourseid(course.getCourseid());
                score.setStudentid(userid);
                scoreService.addScore(score);
            }
        } else {
            throw new BusinessException("修改失败", Code.BUSINESS_ERR);
        }
        Boolean flag = courseService.updateCourse(course);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }

    @PostMapping("/course")
    @Transactional
    public Result addCourse(@RequestBody Course course) {
        if (teacherInfoService.getTeacherById(course.getTeacherid()) == null) {
            throw new BusinessException("该教师不存在，请检查后重试", Code.BUSINESS_ERR);
        }
        Boolean flag = courseService.addCourse(course);
        log.info("添加课程班级：" + course.getClassid());
        if (flag && course.getClassid() != null) {
            List<Integer> list = studentInfoService.selectUserId(course.getClassid());
            Score score = new Score();
            for (Integer studentid : list) {
                score.setCourseid(course.getCourseid());
                score.setStudentid(studentid);
                scoreService.addScore(score);
            }
        } else {
            throw new BusinessException("添加错误", Code.BUSINESS_ERR);
        }
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    @DeleteMapping("/course/{courseid}")
    @Transactional
    public Result deleteCourse(@PathVariable Integer courseid) {
        scoreService.deleteByCourseId(courseid);
        courseTableService.deleteCourseTableByCourseId(courseid);
        Boolean flag = courseService.deleteCourse(courseid);
        if (!flag) {
            throw new BusinessException("删除错误", Code.BUSINESS_ERR);
        }
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }

    @GetMapping("/courseTab/{courseid}")
    public Result getCourseVOById(@PathVariable Integer courseid){
        CourseVO course = courseService.getCourseVOById(courseid);
        Integer code = course != null ? Code.GET_OK : Code.GET_ERR;
        String msg = course != null ? "查询成功" : "查询失败，请重试";
        return new Result(code,course,msg);
    }

    @GetMapping("/course/{courseid}")
    public Result getCourseById(@PathVariable Integer courseid) {
        Course course = courseService.getCourseById(courseid);
        Integer code = course != null ? Code.GET_OK : Code.GET_ERR;
        String msg = course != null ? "查询成功" : "查询失败，请重试";
        return new Result(code, course, msg);
    }
}
