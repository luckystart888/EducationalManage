package com.educationalmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educationalmanage.dao.StudentInfoDao;
import com.educationalmanage.domain.Student;
import com.educationalmanage.domain.vo.StudentVO;
import com.educationalmanage.domain.vo.TeacherVO;
import com.educationalmanage.service.StudentInfoService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentInfoServiceImpl implements StudentInfoService {

    @Autowired
    private StudentInfoDao studentInfoDao;

    @Override
    public Boolean addInfo(Student student) {
        return studentInfoDao.insert(student) > 0;
    }

    @Override
    public Boolean deleteInfo(Integer userid) {
        return studentInfoDao.deleteById(userid) > 0;
    }

    @Override
    public Boolean updateInfo(Student student) {
        return studentInfoDao.updateById(student) > 0;
    }

    @Override
    public Integer selectByUserId(Integer userid) {
        LambdaQueryWrapper<Student> lqw = new LambdaQueryWrapper<>();
        lqw.select(Student::getUserid).eq(Student::getUserid,userid);
        return studentInfoDao.selectCount(lqw);
    }

    @Override
    public List<Integer> selectUserId(Integer classid) {
        return studentInfoDao.selectUserid(classid);
    }

    @Override
    public IPage<StudentVO> getStuPage(int currentPage, int pageSize, StudentVO studentVO) {
        QueryWrapper<StudentVO> qw = new QueryWrapper();
        qw.apply("student.classid=class.id");
        qw.like(Strings.isNotEmpty(studentVO.getName()),"student.name",studentVO.getName());
        qw.eq(Strings.isNotEmpty(studentVO.getGender()),"gender",studentVO.getGender());
        qw.eq(Strings.isNotEmpty(studentVO.getClassName()),"class.classname",studentVO.getClassName());
        IPage<StudentVO> page = new Page<>(currentPage,pageSize);
        return studentInfoDao.getStuPage(page,qw);
    }

    @Override
    public StudentVO getStudentVOById(Integer userid) {
        QueryWrapper<StudentVO> qw = new QueryWrapper<>();
        qw.eq("userid",userid);
        qw.apply("classid = class.id");
        return studentInfoDao.getStudentVOById(userid,qw);
    }

    @Override
    public Student getStudentById(Integer userid) {
        return studentInfoDao.selectById(userid);
    }

}
