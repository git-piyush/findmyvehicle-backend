package com.findmyvehicle.controller;

import com.findmyvehicle.dto.Response;
import com.findmyvehicle.dto.Status;
import com.findmyvehicle.dto.UserProfile;
import com.findmyvehicle.dto.home.DashboardData;
import com.findmyvehicle.dto.vehicle.VehicleDto;
import com.findmyvehicle.entity.Address;
import com.findmyvehicle.entity.User;
import com.findmyvehicle.exception.DuplicateResourceException;
import com.findmyvehicle.exception.ResourceNotFoundException;
import com.findmyvehicle.service.UserService;
import com.findmyvehicle.util.MapperService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private MapperService mapperService;

    @GetMapping("/{id}")
    public ResponseEntity<Response> getUserProfile(@PathVariable Long id) {
        UserProfile data = userService.getUserProfile(id);
        Status status = new Status();
        status.setStatus(HttpStatus.OK.value());
        status.setMessage("User Profile retrieved.");

        Response response = new Response();
        response.setStatus(status);
        response.setData(data);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PreAuthorize("hasAnyRole('NORMAL', 'ADMIN')")
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response> updateUserProfile(@RequestPart("userProfile") @Valid UserProfile userProfile,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {

        User user = userService.findById(userProfile.getId());
        if (user==null) {
            throw new ResourceNotFoundException("User Not Exist.");
        }

        user = mapperService.userProfileToUser(userProfile, user);

        user = userService.createUserProfile(user,imageFile);

        Status status = new Status();
        status.setStatus(HttpStatus.CREATED.value());
        status.setMessage("Profile has been updated.");

        Response response = new Response();
        response.setStatus(status);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
