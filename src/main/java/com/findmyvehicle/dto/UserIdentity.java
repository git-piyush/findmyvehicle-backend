package com.findmyvehicle.dto;

import com.findmyvehicle.enums.UserRole;
import lombok.Data;

@Data
public class UserIdentity {
    private Long userId;
    private String email;
    private String token;
    private UserRole role;
    private String userName;
}
