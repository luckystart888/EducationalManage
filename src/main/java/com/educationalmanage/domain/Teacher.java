package com.educationalmanage.domain;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Teacher {
    @TableId(value = "userid")
    private Integer userid;
    private String name;
    private String gender;
    private String tel;
    private Integer departmentid;
    private String title;
}
