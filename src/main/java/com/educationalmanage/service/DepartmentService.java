package com.educationalmanage.service;

import com.educationalmanage.domain.Department;

import java.util.List;

public interface DepartmentService {
    Integer selectIdByName(String departmentName);
    List<Department> getDepartment();
}
