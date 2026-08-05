package com.findmyvehicle.controller;

import com.findmyvehicle.dto.Response;
import com.findmyvehicle.dto.Status;
import com.findmyvehicle.dto.home.Data;
import com.findmyvehicle.dto.vehicle.VehicleDto;
import com.findmyvehicle.exception.DuplicateResourceException;
import com.findmyvehicle.service.home.HomeService;
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
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/data")
    public ResponseEntity<Response> reportMissingVehicle() {
        Data data = new Data();
        Status status = new Status();
        status.setStatus(HttpStatus.OK.value());
        status.setMessage("Home Dashboard Data retrieved.");

        Response response = new Response();
        response.setStatus(status);
        response.setData(data);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/refresh")
    public ResponseEntity<Response> refreshHomeDashboardData() {
        // Call the service to refresh dashboard data
        homeService.refreshHomeDashboardData();

        // Prepare status object
        Status status = new Status();
        status.setStatus(HttpStatus.OK.value());
        status.setMessage("Home Dashboard Data refreshed successfully.");

        // Prepare data object (optional: you can populate with updated dashboard info if needed)
        Data data = new Data();
        // Example: data.setDashboardInfo(homeRepository.findFirstByOrderByIdAsc());

        // Build response
        Response response = new Response();
        response.setStatus(status);

        // Return response entity
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
