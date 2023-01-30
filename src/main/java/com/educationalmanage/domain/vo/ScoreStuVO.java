package com.educationalmanage.domain.vo;

import lombok.Data;

@Data
public class ScoreStuVO {
    private Integer id;
    private String courseName;
    private Integer teacherid;
    private String name;
    private Double score;
    private Double credit;
}
