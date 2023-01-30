package com.educationalmanage.domain.vo;

import lombok.Data;

@Data
public class ScoreTeaVO {
    private Integer id;
    private Integer courseid;
    private String courseName;
    private Integer studentid;
    private String name;
    private String classname;
    private Double score;
    private Double credit;
}
