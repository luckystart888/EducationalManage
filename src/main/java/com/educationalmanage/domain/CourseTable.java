package com.educationalmanage.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("coursetable")
public class CourseTable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer courseid;
    private String coursetime;
    private String classroom;
}
