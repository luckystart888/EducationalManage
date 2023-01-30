package com.educationalmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.educationalmanage.domain.Student;
import com.educationalmanage.domain.vo.StudentVO;

import java.util.List;

public interface StudentInfoService {
    Boolean addInfo(Student student);
    Boolean deleteInfo(Integer userid);
    Boolean updateInfo(Student student);
    Integer selectByUserId(Integer userid);
    List<Integer> selectUserId(Integer classid);
    Student getStudentById(Integer userid);
    IPage<StudentVO> getStuPage(int currentPage, int pageSize, StudentVO studentVO);
    StudentVO getStudentVOById(Integer userid);
}
