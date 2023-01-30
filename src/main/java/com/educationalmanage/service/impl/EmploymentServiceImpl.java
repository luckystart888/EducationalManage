package com.educationalmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educationalmanage.dao.EmploymentDao;
import com.educationalmanage.domain.Employment;
import com.educationalmanage.domain.vo.EmploymentVO;
import com.educationalmanage.service.EmploymentService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmploymentServiceImpl implements EmploymentService {
    @Autowired
    private EmploymentDao employmentDao;

    @Override
    public Boolean addEmployment(Employment employment) {
        return employmentDao.insert(employment) > 0;
    }

    @Override
    public Boolean deleteEmployment(Integer studentid) {
        LambdaQueryWrapper<Employment> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Employment::getStudentid,studentid);
        return employmentDao.delete(lqw) > 0;
    }

    @Override
    public Boolean updateEmployment(Employment employment) {
        LambdaQueryWrapper<Employment> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Employment::getStudentid,employment.getStudentid());
        return employmentDao.update(employment,lqw) > 0;
    }

    @Override
    public IPage<EmploymentVO> getEmploymentPage(int currentPage, int pageSize, EmploymentVO employmentVO) {
        QueryWrapper<EmploymentVO> qw = new QueryWrapper<>();
        qw.apply("employment.studentid = userid").apply("student.classid = class.id");
        qw.like(Strings.isNotEmpty(employmentVO.getName()),"student.name",employmentVO.getName());
        qw.eq(Strings.isNotEmpty(employmentVO.getClassname()),"class.classname",employmentVO.getClassname());
        IPage<EmploymentVO> page = new Page<>(currentPage,pageSize);
        return employmentDao.getEmploymentPage(page,qw);
    }

    @Override
    public EmploymentVO getEmployment(Integer studentid) {
        LambdaQueryWrapper<Employment> lqw = new LambdaQueryWrapper<>();
        lqw.apply("employment.studentid = userid").apply("student.classid = class.id");
        lqw.eq(Employment::getStudentid,studentid);
        return employmentDao.getEmployment(lqw);
    }
}
