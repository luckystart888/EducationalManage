package com.educationalmanage.service;

import com.educationalmanage.domain.User;
import com.educationalmanage.domain.vo.UserVO;

public interface UserService {
    Boolean addUser(User user);

    Boolean deleteUser(Integer userid);

    Boolean resetPassword(User user);

    Integer selectUserId(Integer userid);

    User getByUserid(Integer userid);

    UserVO getStuUserVO(Integer userid);
    UserVO getTeaUserVO(Integer userid);
}
