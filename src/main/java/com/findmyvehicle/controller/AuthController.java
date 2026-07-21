package com.findmyvehicle.controller;

import com.findmyvehicle.dto.LoginRequest;
import com.findmyvehicle.dto.RegisterRequest;
import com.findmyvehicle.dto.Response;
import com.findmyvehicle.dto.Status;
import com.findmyvehicle.entity.User;
import com.findmyvehicle.service.serviceImpl.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody @Valid RegisterRequest registerRequest){
        Status status = new Status();
        Response response = new Response();
        try{
           User user = userService.registerUser(registerRequest);
            status.setStatus(200);
            status.setMessage("User has been registerd sucessfully.");
            response.setStatus(status);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            status.setStatus(500);
            status.setMessage("Internal Server Error.");
            response.setStatus(status);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody @Valid LoginRequest loginRequest){
        return ResponseEntity.ok(userService.loginUser(loginRequest));
    }
}
