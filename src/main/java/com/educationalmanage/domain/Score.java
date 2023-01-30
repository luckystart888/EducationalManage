package com.educationalmanage.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Score {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer courseid;
    private Integer studentid;
    private Double score;
    private Double credit;
}
