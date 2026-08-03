package com.findmyvehicle.util;

import com.findmyvehicle.entity.User;
import com.findmyvehicle.exception.ResourceNotFoundException;
import com.findmyvehicle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MultiFunctionUtility{

    @Autowired
    private UserRepository userRepository;

    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return user;
    }
}
