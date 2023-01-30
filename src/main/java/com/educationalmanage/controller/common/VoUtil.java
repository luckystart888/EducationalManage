package com.educationalmanage.controller.common;

import com.educationalmanage.domain.*;
import com.educationalmanage.domain.vo.*;
import com.educationalmanage.service.ClassService;
import com.educationalmanage.service.DepartmentService;
import com.educationalmanage.service.TeacherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

//TODO 解决Util service 空指针异常
@Component
public class VoUtil {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ClassService classService;

    private static VoUtil voUtil = new VoUtil();

    @PostConstruct
    public void init(){
        voUtil.classService = this.classService;
        voUtil.departmentService = this.departmentService;
    }



    public static CourseTable getToCourseTable(CourseTableVO courseTableVO){
        CourseTable courseTable = new CourseTable();
        courseTable.setId(courseTableVO.getId());
        courseTable.setCourseid(courseTableVO.getCourseid());
        courseTable.setCoursetime(courseTableVO.getCoursetime());
        courseTable.setClassroom(courseTableVO.getClassroom());
        return courseTable;
    }

    public static Course getToCourse(HttpServletRequest request, CourseVO courseVO){
        Integer classid = voUtil.classService.selectIdByName(courseVO.getClassname());
        Course course = new Course();
        course.setCourseid(courseVO.getCourseid());
        course.setCoursename(courseVO.getCoursename());
        course.setTeacherid((Integer) request.getSession().getAttribute("userid"));
        course.setCourseweek(courseVO.getCourseweek());
        course.setCoursetype(courseVO.getCoursetype());
        course.setClassid(classid);
        course.setCredit(courseVO.getCredit());
        return course;
    }

    public static Score getToScore(ScoreAdVO scoreAdVO){
        Score score = new Score();
        score.setId(scoreAdVO.getId());
        score.setScore(scoreAdVO.getScore());
        score.setCourseid(scoreAdVO.getCourseid());
        score.setStudentid(scoreAdVO.getStudentid());
        return score;
    }

    public static Score getToScore(ScoreTeaVO scoreTeaVO){
        Score score = new Score();
        score.setId(scoreTeaVO.getId());
        score.setScore(scoreTeaVO.getScore());
        score.setCourseid(scoreTeaVO.getCourseid());
        score.setStudentid(scoreTeaVO.getStudentid());
        return score;
    }

    public static Teacher getToTeacher(TeacherVO teacherVO){
        Integer departmentId = voUtil.departmentService.selectIdByName(teacherVO.getDepartmentName());
        Teacher teacher = new Teacher();
        teacher.setUserid(teacherVO.getUserid());
        teacher.setName(teacherVO.getName());
        teacher.setGender(teacherVO.getGender());
        teacher.setTel(teacherVO.getTel());
        teacher.setDepartmentid(departmentId);
        teacher.setTitle(teacherVO.getTitle());
        return teacher;
    }

    public static Student getToStudent(StudentVO studentVO) {
        Integer classId = voUtil.classService.selectIdByName(studentVO.getClassName());
        Student student = new Student();
        student.setName(studentVO.getName());
        student.setUserid(studentVO.getUserid());
        student.setGender(studentVO.getGender());
        student.setBirthday(studentVO.getBirthday());
        student.setIndate(studentVO.getIndate());
        student.setClassid(classId);
        student.setTel(studentVO.getTel());
        return student;
    }

    public static Employment getToEmployment(EmploymentVO employmentVO){
        Employment employment = new Employment();
        employment.setStudentid(employmentVO.getStudentid());
        employment.setWorkprovince(employmentVO.getWorkprovince());
        employment.setWorkunit(employmentVO.getWorkunit());
        employment.setWages(employmentVO.getWages());
        return employment;
    }

}
