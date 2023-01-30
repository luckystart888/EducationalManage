package com.educationalmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educationalmanage.dao.TeacherInfoDao;
import com.educationalmanage.domain.Student;
import com.educationalmanage.domain.Teacher;
import com.educationalmanage.domain.vo.TeacherVO;
import com.educationalmanage.service.TeacherInfoService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TeacherInfoServiceImpl implements TeacherInfoService {

    @Autowired
    private TeacherInfoDao teacherInfoDao;

    @Override
    public Boolean addInfo(Teacher teacher) {
        return teacherInfoDao.insert(teacher) > 0;
    }

    @Override
    public Boolean deleteInfo(Integer userid) {
        return teacherInfoDao.deleteById(userid) > 0;
    }

    @Override
    public Boolean updateInfo(Teacher teacher) {
        return teacherInfoDao.updateById(teacher) > 0;
    }

    @Override
    public TeacherVO getTeacherById(Integer userid) {
        QueryWrapper<TeacherVO> qw = new QueryWrapper<>();
        qw.eq("userid",userid);
        qw.apply("departmentid = department.id");
        return teacherInfoDao.getTeacherById(userid,qw);
    }

    @Override
    public IPage<TeacherVO> getPage(int currentPage, int pageSize, TeacherVO teacherVO) {
        QueryWrapper<TeacherVO> qw = new QueryWrapper<>();
        qw.eq(Strings.isNotEmpty(teacherVO.getGender()),"gender",teacherVO.getGender());
        qw.like(Strings.isNotEmpty(teacherVO.getName()),"name",teacherVO.getName());
        qw.eq(Strings.isNotEmpty(teacherVO.getDepartmentName()),"departmentname",teacherVO.getDepartmentName());
        qw.apply("departmentid = department.id");
        IPage<TeacherVO> page = new Page(currentPage,pageSize);
        teacherInfoDao.getTeaPage(page,qw);
        return page;
    }

    @Override
    public Integer selectUserId(Integer userid) {
        LambdaQueryWrapper<Teacher> lqw = new LambdaQueryWrapper<>();
        lqw.select(Teacher::getUserid).eq(Teacher::getUserid,userid);
        return teacherInfoDao.selectCount(lqw);
    }


}
