package com.educationalmanage.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Department {
    @TableId
    private Integer id;
    @TableField("departmentname")
    private String departmentName;
}
