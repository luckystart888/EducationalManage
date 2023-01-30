package com.educationalmanage.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Role {
    @TableId
    private Integer id;
    private String type;
}
