package com.educationalmanage.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Course {
    @TableId(value = "courseid", type = IdType.AUTO)
    private Integer courseid;
    private String coursename;
    private Integer teacherid;
    private String coursetype;
    private String courseweek;
    private Integer classid;
    private Double credit;
}
