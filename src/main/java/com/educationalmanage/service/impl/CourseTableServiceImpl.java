package com.educationalmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educationalmanage.dao.CourseTableDao;
import com.educationalmanage.domain.CourseTable;
import com.educationalmanage.domain.vo.CourseTableVO;
import com.educationalmanage.service.CourseTableService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseTableServiceImpl implements CourseTableService {

    @Autowired
    private CourseTableDao courseTableDao;

    @Override
    public IPage<CourseTableVO> getStuCourseTablePage(int currentPage, int pageSize, Integer userid) {
        QueryWrapper<CourseTableVO> qw = new QueryWrapper<>();
        qw.apply("teacher.userid = course.teacherid");
        qw.apply("class.id = student.classid");
        qw.apply("course.courseid = coursetable.courseid");
        qw.eq("student.userid",userid);
        IPage<CourseTableVO> page = new Page<>(currentPage,pageSize);
        return courseTableDao.getStuCourseTablePage(page,qw);
    }

    @Override
    public IPage<CourseTableVO> getTeaCourseTablePage(int currentPage, int pageSize, CourseTableVO courseTableVO, Integer userid) {
        QueryWrapper<CourseTableVO> qw = new QueryWrapper();
        qw.apply("course.classid = class.id");
        qw.apply("course.teacherid = teacher.userid");
        qw.apply("course.courseid = coursetable.courseid");
        qw.eq("teacher.userid",userid);
        qw.like(Strings.isNotEmpty(courseTableVO.getCoursename()),"course.coursename",courseTableVO.getCoursename());
        qw.eq(Strings.isNotEmpty(courseTableVO.getClassname()),"class.classname",courseTableVO.getClassname());
        IPage<CourseTableVO> page = new Page<>(currentPage,pageSize);
        return courseTableDao.getTeaCourseTablePage(page,qw);
    }

    @Override
    public IPage<CourseTableVO> getAdCourseTablePage(int currentPage, int pageSize, CourseTableVO courseTableVO) {
        QueryWrapper<CourseTableVO> qw = new QueryWrapper<>();
        qw.apply("course.classid = class.id");
        qw.apply("course.teacherid = teacher.userid");
        qw.apply("course.courseid = coursetable.courseid");
        qw.like(Strings.isNotEmpty(courseTableVO.getCoursename()),"course.coursename", courseTableVO.getCoursename());
        qw.like(Strings.isNotEmpty(courseTableVO.getName()),"teacher.name", courseTableVO.getName());
        qw.eq(Strings.isNotEmpty(courseTableVO.getClassname()),"class.classname", courseTableVO.getClassname());
        IPage<CourseTableVO> page = new Page<>(currentPage,pageSize);
        return courseTableDao.getAdCourseTablePage(page,qw);
    }

    @Override
    public CourseTableVO getCourseTableVOById(Integer id) {
        QueryWrapper<CourseTableVO> qw = new QueryWrapper<>();
        qw.eq("coursetable.id",id);
        qw.apply("course.classid = class.id");
        qw.apply("course.teacherid = teacher.userid");
        qw.apply("course.courseid = coursetable.courseid");
        return courseTableDao.getCourseTableVOById(qw);
    }

    @Override
    public Boolean addCourseTable(CourseTable courseTable) {
        return courseTableDao.insert(courseTable) > 0;
    }

    @Override
    public Boolean updateCourseTable(CourseTable courseTable) {
        return courseTableDao.updateById(courseTable) > 0;
    }

    @Override
    public Boolean deleteCourseTableById(Integer id) {
        return courseTableDao.deleteById(id) > 0;
    }

    @Override
    public Boolean deleteCourseTableByCourseId(Integer courseid) {
        LambdaQueryWrapper<CourseTable> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CourseTable::getCourseid,courseid);
        return courseTableDao.delete(lqw) > 0;
    }
}
