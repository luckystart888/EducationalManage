package com.educationalmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.educationalmanage.domain.Course;
import com.educationalmanage.domain.CourseTable;
import com.educationalmanage.domain.vo.CourseTableVO;
import com.educationalmanage.domain.vo.CourseVO;

public interface CourseTableService {
    IPage<CourseTableVO> getStuCourseTablePage(int currentPage, int pageSize, Integer userid);

    IPage<CourseTableVO> getTeaCourseTablePage(int currentPage, int pageSize, CourseTableVO courseTableVO, Integer userid);

    IPage<CourseTableVO> getAdCourseTablePage(int currentPage, int pageSize, CourseTableVO courseTableVO);

    CourseTableVO getCourseTableVOById(Integer id);

    Boolean addCourseTable(CourseTable courseTable);

    Boolean updateCourseTable(CourseTable courseTable);

    Boolean deleteCourseTableById(Integer id);

    Boolean deleteCourseTableByCourseId(Integer courseid);
}
