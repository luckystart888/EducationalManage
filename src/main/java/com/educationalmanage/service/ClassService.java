package com.educationalmanage.service;

import com.educationalmanage.domain.Class;

import java.util.List;

public interface ClassService {
    Integer selectIdByName(String className);

    List<Class> getClas();
}
