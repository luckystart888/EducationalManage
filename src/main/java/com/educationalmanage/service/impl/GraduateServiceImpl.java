package com.educationalmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educationalmanage.dao.GraduateDao;
import com.educationalmanage.domain.Class;
import com.educationalmanage.domain.Graduate;
import com.educationalmanage.domain.Student;
import com.educationalmanage.domain.vo.GraduateVO;
import com.educationalmanage.service.GraduateService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GraduateServiceImpl implements GraduateService {

    @Autowired
    private GraduateDao graduateDao;

    @Override
    public Boolean addGraduate(Graduate graduate) {
        return graduateDao.insert(graduate) > 0;
    }

    @Override
    public Boolean deleteGraduate(Integer studentid) {
        LambdaQueryWrapper<Graduate> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Graduate::getStudentid,studentid);
        return graduateDao.delete(lqw) > 0;
    }

    @Override
    public GraduateVO selectByUserid(Integer studentid) {
        LambdaQueryWrapper<Graduate> lqw = new LambdaQueryWrapper<>();
        lqw.apply("graduate.studentid = student.userid").apply("student.classid = class.id");
        lqw.eq(Graduate::getStudentid,studentid);
        return graduateDao.selectGraduateByUserid(lqw);
    }

    @Override
    public Boolean totalCredit(Integer studentid) {
        return graduateDao.totalCredit(studentid) > 0;
    }

    @Override
    public IPage<GraduateVO> getGraduate(int currentPage, int pageSize, GraduateVO graduateVO) {
        QueryWrapper<GraduateVO> qw = new QueryWrapper<>();
        qw.apply("graduate.studentid = student.userid").apply("student.classid = class.id");
        qw.eq(Strings.isNotEmpty(graduateVO.getClassname()),"class.classname",graduateVO.getClassname());
        qw.eq(Strings.isNotEmpty(graduateVO.getName()),"student.name",graduateVO.getName());
        IPage<GraduateVO> page = new Page<>(currentPage,pageSize);
        return graduateDao.getGraduatePage(page,qw);
    }

    @Override
    public Boolean updateGraduate(Graduate graduate) {
        LambdaQueryWrapper<Graduate> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Graduate::getStudentid,graduate.getStudentid());
        return graduateDao.update(graduate,lqw) > 0;
    }
}
