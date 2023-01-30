package com.educationalmanage.controller.common;


import com.educationalmanage.exception.BusinessException;
import com.educationalmanage.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException se){
        return new Result(se.getCode(),null,se.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException be){
        return new Result(be.getCode(),null,be.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result doException(Exception exception){
        return new Result(Code.SYSTEM_UNKNOWN_ERR,null,"网络繁忙，请稍后重试");
    }
}
