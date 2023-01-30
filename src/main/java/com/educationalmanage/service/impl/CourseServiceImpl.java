package com.educationalmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educationalmanage.dao.CourseDao;
import com.educationalmanage.domain.Course;
import com.educationalmanage.domain.vo.CourseVO;
import com.educationalmanage.service.CourseService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Override
    public IPage<CourseVO> getTeaCourseVOPage(int currentPage, int pageSize, CourseVO courseVO, Integer userid) {
        QueryWrapper<CourseVO> qw = new QueryWrapper<>();
        qw.apply("teacher.userid = course.teacherid");
        qw.apply("class.id = course.classid");
        qw.eq("teacher.userid",userid);
        qw.eq(Strings.isNotEmpty(courseVO.getClassname()),"class.classname",courseVO.getClassname());
        qw.like(Strings.isNotEmpty(courseVO.getCoursename()),"course.coursename",courseVO.getCoursename());
        IPage<CourseVO> page = new Page<>(currentPage,pageSize);
        return courseDao.getTeaCourseVOPage(page,qw);
    }

    @Override
    public IPage<CourseVO> getAdCourseVOPage(int currentPage, int pageSize, CourseVO courseVO) {
        QueryWrapper<CourseVO> qw = new QueryWrapper<>();
        qw.apply("teacher.userid = course.teacherid");
        qw.apply("class.id = course.classid");
        qw.eq(Strings.isNotEmpty(courseVO.getClassname()),"class.classname",courseVO.getClassname());
        qw.like(Strings.isNotEmpty(courseVO.getName()),"teacher.name",courseVO.getName());
        qw.like(Strings.isNotEmpty(courseVO.getCoursename()),"course.coursename",courseVO.getCoursename());
        IPage<CourseVO> page = new Page<>(currentPage,pageSize);
        return courseDao.getAdCourseVOPage(page,qw);
    }

    @Override
    public Boolean updateCourse(Course course) {
        return courseDao.updateById(course) > 0;
    }

    @Override
    public Boolean addCourse(Course course) {
        return courseDao.insert(course) > 0;
    }

    @Override
    public Boolean deleteCourse(Integer courseid,Integer teacherid) {
        LambdaQueryWrapper<Course> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Course::getTeacherid,teacherid);
        lqw.eq(Course::getCourseid,courseid);
        return courseDao.delete(lqw) > 0;
    }

    @Override
    public Boolean deleteCourse(Integer courseid) {
        return courseDao.deleteById(courseid) > 0;
    }

    @Override
    public Double getCredit(Integer courseid) {
        Course course = courseDao.selectById(courseid);
        return course.getCredit();
    }

    @Override
    public List<Integer> getCourseid(Integer classid) {
        return courseDao.getCourseid(classid);
    }

    @Override
    public Boolean deleteByTeacherId(Integer teacherid) {
        LambdaQueryWrapper<Course> lqw = new LambdaQueryWrapper();
        lqw.eq(Course::getTeacherid,teacherid);
        return courseDao.delete(lqw) > 0;
    }

    @Override
    public List<Integer> selectIdByTeacherid(Integer teacherid) {
        return courseDao.selectIdByTeacherid(teacherid);
    }

    @Override
    public List<Course> getByTeacherid(Integer teacherid) {
        return courseDao.getByTeacherid(teacherid);
    }

    @Override
    public List<Course> getCourse() {
        return courseDao.getCourse();
    }

    @Override
    public CourseVO getCourseVOById(Integer courseid) {
        QueryWrapper<CourseVO> qw = new QueryWrapper<>();
        qw.apply("teacher.userid = course.teacherid");
        qw.apply("class.id = course.classid");
        qw.eq("course.courseid",courseid);
        return courseDao.getCourseVOById(qw);
    }

    @Override
    public Course getCourseById(Integer courseid) {
        return courseDao.selectById(courseid);
    }

}
