package com.educationalmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.educationalmanage.domain.Graduate;
import com.educationalmanage.domain.vo.GraduateVO;

public interface GraduateService {

    Boolean addGraduate(Graduate graduate);
    Boolean deleteGraduate(Integer studentid);
    GraduateVO selectByUserid(Integer studentid);
    Boolean totalCredit(Integer studentid);
    IPage<GraduateVO> getGraduate(int currentPage, int pageSize, GraduateVO graduateVO);
    Boolean updateGraduate(Graduate graduate);
}
