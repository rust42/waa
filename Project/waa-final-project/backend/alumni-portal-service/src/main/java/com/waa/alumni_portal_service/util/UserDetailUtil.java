package com.waa.alumni_portal_service.util;

import com.waa.alumni_portal_service.dto.StudentDTO;
import com.waa.alumni_portal_service.dto.UserDTO;
import com.waa.alumni_portal_service.security.MyUserDetails;
import com.waa.alumni_portal_service.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailUtil {

    private final SecurityUtil securityUtil;

    public StudentDTO getCurrentStudent() {
        StudentDTO student = new StudentDTO();
        MyUserDetails myUserDetails = securityUtil.getCurrentUser();
        student.setId(myUserDetails.getId());
        return student;
    }

    public UserDTO getCurrentUser() {
        UserDTO userDTO = new UserDTO();
        MyUserDetails myUserDetails = securityUtil.getCurrentUser();
        userDTO.setId(myUserDetails.getId());
        return userDTO;
    }
}
