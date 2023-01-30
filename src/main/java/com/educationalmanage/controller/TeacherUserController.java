package com.educationalmanage.controller;

import com.educationalmanage.controller.common.Code;
import com.educationalmanage.controller.common.Result;
import com.educationalmanage.domain.vo.TeacherVO;
import com.educationalmanage.service.TeacherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher/user")
public class TeacherUserController {

    @Autowired
    private TeacherInfoService teacherInfoService;

    @GetMapping("/info/{userid}")
    public Result getStudentById(@PathVariable Integer userid){
        TeacherVO teacherById = teacherInfoService.getTeacherById(userid);
        Integer code = teacherById != null ? Code.GET_OK : Code.GET_ERR;
        String msg = teacherById != null ? "查询成功" : "查询失败，请重试";
        return new Result(code,teacherById,msg);
    }
}
