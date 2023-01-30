package com.educationalmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.educationalmanage.domain.Course;
import com.educationalmanage.domain.Student;
import com.educationalmanage.domain.vo.CourseTableVO;
import com.educationalmanage.domain.vo.CourseVO;

import java.util.List;


public interface CourseService {
    IPage<CourseVO> getTeaCourseVOPage(int currentPage, int pageSize, CourseVO courseVO,Integer userid);

    IPage<CourseVO> getAdCourseVOPage(int currentPage, int pageSize, CourseVO courseVO);

    Boolean updateCourse(Course course);

    Boolean addCourse(Course course);

    Boolean deleteCourse(Integer courseid,Integer teacherid);

    Boolean deleteCourse(Integer courseid);

    Double getCredit(Integer courseid);

    List<Integer> getCourseid(Integer classid);

    Boolean deleteByTeacherId(Integer teacherid);

    List<Integer> selectIdByTeacherid(Integer teacherid);

    List<Course>getByTeacherid(Integer teacherid);

    List<Course>getCourse();

    CourseVO getCourseVOById(Integer courseid);

    Course getCourseById(Integer courseid);
}
