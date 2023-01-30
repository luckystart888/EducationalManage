package com.educationalmanage.controller;
import com.educationalmanage.controller.common.Code;
import com.educationalmanage.controller.common.Result;
import com.educationalmanage.domain.User;
import com.educationalmanage.service.LoginService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@Log4j2
public class UserController {

    @Autowired
    private LoginService loginService;

    @PostMapping ("/login")
    public Result login(HttpServletRequest request, @RequestParam("username") Integer userid, @RequestParam("password") String password){
        User login = loginService.login(userid, password);
        Integer code = login != null ? Code.LOGIN_OK : Code.LOGIN_ERR;
        String msg = login != null ? "登录成功" : "登录失败，账号或密码错误";
        if (login != null) {
            request.getSession().setAttribute("userid", login.getUserid());
            request.getSession().setAttribute("password", login.getPassword());
            request.getSession().setAttribute("role", login.getRole());
            log.info("登录成功");
        }
        return new Result(code, login, msg);
    }

    @PostMapping("/logout")
    public Result logout(HttpServletRequest request){
        request.getSession().removeAttribute("userid");
        request.getSession().removeAttribute("password");
        request.getSession().removeAttribute("role");
        log.info("退出成功");
        return new Result(Code.LOGOUT,null,"退出成功");
    }

    @PostMapping("/updatePwd")
    public Result updatePassword(HttpServletRequest request,  @RequestParam("oldPwd") String oldPassword,@RequestParam("newPwd") String newPassword) {
        Integer userid = (Integer) request.getSession().getAttribute("userid");
        String password = request.getSession().getAttribute("password").toString();
        if (password.equals(oldPassword)){
            Boolean flag = loginService.updatePasswd(userid, newPassword);
            request.getSession().removeAttribute("userid");
            request.getSession().removeAttribute("password");
            request.getSession().removeAttribute("role");
            String msg = flag ? "修改成功，请重新登录" : "修改失败";
            return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR,flag,msg);
        }else {
            return new Result(Code.UPDATE_ERR,false,"原始密码错误");
        }
    }


}
