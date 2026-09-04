package com.findmyvehicle.service;

import com.findmyvehicle.dto.LoginRequest;
import com.findmyvehicle.dto.RegisterRequest;
import com.findmyvehicle.dto.Response;
import com.findmyvehicle.dto.UserProfile;
import com.findmyvehicle.entity.User;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    User registerUser(RegisterRequest registerRequest);

    Response loginUser(LoginRequest loginRequest);

    Boolean existsByEmail(String email);

    Boolean existsByEmailAndId(String email, Long id);

    UserDetails findOrCreateSocialUser(String email, String name, String provider);

    UserProfile getUserProfile(Long id);

    User createUserProfile(User user, MultipartFile imageFile);

    User findById(Long id);
}
