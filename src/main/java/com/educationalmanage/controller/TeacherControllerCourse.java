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
import com.educationalmanage.domain.vo.ScoreTeaVO;
import com.educationalmanage.exception.BusinessException;
import com.educationalmanage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherControllerCourse {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseTableService courseTableService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private StudentInfoService studentInfoService;

    @GetMapping("/courseTable/{currentPage}/{pageSize}")
    public Result getTeaCourseTablePage(@PathVariable int currentPage, @PathVariable int pageSize, CourseTableVO courseTableVO, HttpServletRequest request) {
        Integer userid = (Integer) request.getSession().getAttribute("userid");
        IPage<CourseTableVO> courseTablePage = courseTableService.getTeaCourseTablePage(currentPage, pageSize, courseTableVO,userid);
        if (currentPage > courseTablePage.getPages()) {
            courseTablePage = courseTableService.getTeaCourseTablePage((int) courseTablePage.getPages(), pageSize, courseTableVO,userid);
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
    public Result addCourseTable(@RequestBody CourseTableVO courseTableVO) {
        CourseTable courseTable = VoUtil.getToCourseTable(courseTableVO);
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
    public Result getTeaCoursePage (HttpServletRequest request, @PathVariable int currentPage, @PathVariable int pageSize, CourseVO courseVO) {
        Integer userid = (Integer) request.getSession().getAttribute("userid");
        IPage<CourseVO> coursePage = courseService.getTeaCourseVOPage(currentPage, pageSize, courseVO,userid);
        if( currentPage > coursePage.getPages()){
            coursePage = courseService.getTeaCourseVOPage((int)coursePage.getPages(), pageSize, courseVO,userid);
        }
        Integer code = coursePage != null ? Code.GET_OK : Code.GET_ERR;
        String msg = coursePage != null ? "查询成功" : "查询失败，请重试";
        return new Result(code,coursePage,msg);
    }

    @PutMapping("/course")
    public Result updateCourse(HttpServletRequest request,@RequestBody CourseVO courseVO){
        CourseVO courseById = courseService.getCourseVOById(courseVO.getCourseid());
        Course course = VoUtil.getToCourse(request, courseVO);
        Score score = new Score();
        if (!courseById.getClassname().equals(courseVO.getClassname())){
            scoreService.deleteByCourseId(course.getCourseid());
            List<Integer> studentList = studentInfoService.selectUserId(course.getClassid());
            for (Integer userid : studentList){
                score.setCourseid(course.getCourseid());
                score.setStudentid(userid);
                scoreService.addScore(score);
            }
        }
        course.setTeacherid((Integer) request.getSession().getAttribute("userid"));
        Boolean flag = courseService.updateCourse(course);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR,flag);
    }

    @PostMapping("/course")
    @Transactional
    public Result addCourse(HttpServletRequest request,@RequestBody CourseVO courseVO){
        Course course = VoUtil.getToCourse(request, courseVO);
        Boolean flag = courseService.addCourse(course);
        if (flag && course.getClassid() != null){
            List<Integer> list = studentInfoService.selectUserId(course.getClassid());
            Score score = new Score();
            for (Integer studentid : list){
                score.setCourseid(course.getCourseid());
                score.setStudentid(studentid);
                scoreService.addScore(score);
            }
        }
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR,flag);
    }

    @DeleteMapping("/course/{courseid}")
    @Transactional
    public Result deleteCourse(HttpServletRequest request,@PathVariable Integer courseid){
        Integer userid = (Integer) request.getSession().getAttribute("userid");
        scoreService.deleteByCourseId(courseid);
        courseTableService.deleteCourseTableByCourseId(courseid);
        Boolean flag = courseService.deleteCourse(courseid,userid);
        if (!flag){
            throw new BusinessException("删除错误",Code.BUSINESS_ERR);
        }
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR,flag);
    }

    @GetMapping("/course/{courseid}")
    public Result getCourseVOById(@PathVariable Integer courseid){
        CourseVO course = courseService.getCourseVOById(courseid);
        Integer code = course != null ? Code.GET_OK : Code.GET_ERR;
        String msg = course != null ? "查询成功" : "查询失败，请重试";
        return new Result(code,course,msg);
    }

}
