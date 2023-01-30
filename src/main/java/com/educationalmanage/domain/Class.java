package com.educationalmanage.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Class {
    @TableId
    private Integer id;
    private Integer majorid;
    private Integer departmentid;
    @TableField("classname")
    private String name;
}
