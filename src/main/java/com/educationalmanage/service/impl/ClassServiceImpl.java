package com.educationalmanage.service.impl;

import com.educationalmanage.dao.ClassDao;
import com.educationalmanage.domain.Class;
import com.educationalmanage.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassDao classDao;

    @Override
    public Integer selectIdByName(String className) {
        return classDao.selectIdByName(className);
    }

    @Override
    public List<Class> getClas() {
        return classDao.selectList(null);
    }
}
