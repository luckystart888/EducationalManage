package com.educationalmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.educationalmanage.domain.Teacher;
import com.educationalmanage.domain.vo.TeacherVO;

public interface TeacherInfoService {
    Boolean addInfo(Teacher teacher);
    Boolean deleteInfo(Integer userid);
    Boolean updateInfo(Teacher teacher);
    TeacherVO getTeacherById(Integer userid);
    IPage<TeacherVO> getPage(int currentPage, int pageSize, TeacherVO teacherVO);
    Integer selectUserId(Integer userid);
}
