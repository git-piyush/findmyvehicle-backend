package com.findmyvehicle.service.serviceImpl;

import com.findmyvehicle.dto.LoginRequest;
import com.findmyvehicle.dto.RegisterRequest;
import com.findmyvehicle.dto.Response;
import com.findmyvehicle.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    User registerUser(RegisterRequest registerRequest);
    Response loginUser(LoginRequest loginRequest);
//    Response getAllUsers();
    User getCurrentLoggedInUser();
//    Response updateUser(Long id, UserDTO userDTO);
//    Response deleteUser(Long id);
    UserDetails findOrCreateSocialUser(String email, String name, String provider);
}
