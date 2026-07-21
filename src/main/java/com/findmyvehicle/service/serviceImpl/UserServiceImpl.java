package com.findmyvehicle.service.serviceImpl;

import com.findmyvehicle.dto.*;
import com.findmyvehicle.entity.User;
import com.findmyvehicle.enums.AuthProvider;
import com.findmyvehicle.enums.UserRole;
import com.findmyvehicle.exception.InvalidCredentialsException;
import com.findmyvehicle.exception.ResourceNotFoundException;
import com.findmyvehicle.repository.UserRepository;
import com.findmyvehicle.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    private final JwtUtils jwtUtils;

    @Override
    public User registerUser(RegisterRequest registerRequest) {

        UserRole role = UserRole.NORMAL;

        if (registerRequest.getRole() != null) {
            role=registerRequest.getRole();
        }
        User userToSave = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .phoneNumber(registerRequest.getPhoneNumber())
                .role(role)
                .build();

       User user = userRepository.save(userToSave);

        return user;
    }

    @Override
    public Response loginUser(LoginRequest loginRequest) {
       User user = userRepository.findByEmail(loginRequest.getEmail())
               .orElseThrow(()-> new ResourceNotFoundException("Email not Found."));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("password does not match");
        }
        String token = jwtUtils.generateToken(user.getEmail());

        UserIdentity identity = new UserIdentity();
        identity.setToken(token);
        identity.setUserName(user.getName());
        identity.setRole(user.getRole());

        Status status = new Status();
        status.setStatus(200);
        status.setMessage("User logged in successfully.");

        return Response.builder().userIdentity(identity)
                .status(status).build();
    }

    @Override
    public User getCurrentLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("User Not Found."));

        return user;
    }

    @Override
    public UserDetails findOrCreateSocialUser(String email, String name, String provider) {

        // Check if user already exists
        Optional<User> existingUser = userRepository.findByEmail(email);

        if (existingUser.isPresent()) {
            log.info("Existing social user found: {}", email);
            return existingUser.get();
        }

        log.info("Creating new social user: email={}, provider={}", email, provider);

        // First time social login → create new user
        User newUser = User.builder()
                .email(email)
                .name(name)
                .password(null)                              // ✅ no password for social users
                .phoneNumber(null)                           // ✅ no phone for social users
                .role(UserRole.NORMAL)                      // ✅ use UserRole enum (default role)
                .provider(AuthProvider.valueOf(provider.toUpperCase()))  // ✅ "google" → AuthProvider.GOOGLE
                .emailVerified(true)                         // ✅ Google emails are pre-verified
                .build();

        return userRepository.save(newUser);
    }
}
