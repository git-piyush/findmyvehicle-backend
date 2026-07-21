package com.findmyvehicle.security;

import com.findmyvehicle.entity.User;
import com.findmyvehicle.exception.ResourceNotFoundException;
import com.findmyvehicle.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(()-> new ResourceNotFoundException("User Email Not Found."));

        return AuthUser.builder()
                .user(user)
                .build();
    }
}
