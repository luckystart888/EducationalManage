package com.educationalmanage.service.impl;

import com.educationalmanage.dao.DepartmentDao;
import com.educationalmanage.domain.Department;
import com.educationalmanage.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public Integer selectIdByName(String departmentName) {
        return departmentDao.selectIdByName(departmentName);
    }

    @Override
    public List<Department> getDepartment() {
        return departmentDao.selectList(null);
    }
}
