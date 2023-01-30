package com.educationalmanage.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class CourseVO {
    @TableId(value = "courseid", type = IdType.AUTO)
    private Integer courseid;
    private String coursename;
    private String name;
    private String coursetype;
    private String courseweek;
    private String classname;
    private Double credit;
}
