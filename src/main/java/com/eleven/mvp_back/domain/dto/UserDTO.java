package com.eleven.mvp_back.domain.dto;

import com.eleven.mvp_back.domain.enums.Role;
import lombok.Data;

@Data
public class UserDTO {
    private String email;
    private String password;
    private Role role;
}

