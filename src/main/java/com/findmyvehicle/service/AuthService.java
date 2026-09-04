package com.findmyvehicle.service;

import com.findmyvehicle.dto.ChangePasswordDto;
import com.findmyvehicle.dto.Response;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    Response changePassword(ChangePasswordDto changePasswordDto);

}
