package com.educationalmanage.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("login")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userid;
    private String password;
    private Integer role;
}
