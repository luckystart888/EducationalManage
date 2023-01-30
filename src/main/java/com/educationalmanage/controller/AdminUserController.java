package com.educationalmanage.controller;

import com.educationalmanage.controller.common.Code;
import com.educationalmanage.controller.common.Result;
import com.educationalmanage.domain.User;
import com.educationalmanage.domain.vo.UserVO;
import com.educationalmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @PutMapping("/resetpassword")
    public Result resetPassword(@RequestBody User user){
        Boolean flag = userService.resetPassword(user);
        String msg = flag ? "重置成功" : "重置失败";
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR,flag,msg);
    }

    @GetMapping("/{userid}")
    public Result getUserByUserid(@PathVariable Integer userid){
        User user = userService.getByUserid(userid);
        Integer code = user != null ? Code.GET_OK : Code.GET_ERR;
        String msg = user != null ? "查询成功" : "未查询到，请重试";
        return new Result(code,user,msg);
    }

    @GetMapping("/userVO/{userid}")
    public Result getUserVOByUserid(@PathVariable Integer userid){
        UserVO teaUserVO = userService.getTeaUserVO(userid);
        UserVO stuUserVO = userService.getStuUserVO(userid);
        UserVO userVO = stuUserVO == null ? teaUserVO : stuUserVO;
        Integer code = userVO != null ? Code.GET_OK : Code.GET_ERR;
        String msg = userVO != null ? "查询成功" : "未查询到，请重试";
        return new Result(code,userVO,msg);
    }
}
