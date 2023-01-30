package com.educationalmanage.domain.vo;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class TeacherVO {
    @TableId(value = "userid")
    private Integer userid;
    private String name;
    private String gender;
    private String tel;
    private String departmentName;
    private String title;
}
