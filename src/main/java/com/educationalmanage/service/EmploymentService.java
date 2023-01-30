package com.educationalmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.educationalmanage.domain.Employment;
import com.educationalmanage.domain.vo.EmploymentVO;

public interface EmploymentService {

    Boolean addEmployment(Employment employment);
    Boolean deleteEmployment(Integer studentid);
    Boolean updateEmployment(Employment employment);
    IPage<EmploymentVO> getEmploymentPage(int currentPage, int pageSize, EmploymentVO employmentVO);
    EmploymentVO getEmployment(Integer studentid);
}
