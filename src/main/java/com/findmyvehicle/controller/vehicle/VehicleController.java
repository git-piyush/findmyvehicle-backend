package com.findmyvehicle.controller.vehicle;

import com.findmyvehicle.dto.Response;
import com.findmyvehicle.dto.Status;
import com.findmyvehicle.dto.vehicle.VehicleDto;
import com.findmyvehicle.dto.vehicle.VehicleImageDto;
import com.findmyvehicle.entity.User;
import com.findmyvehicle.entity.vehicle.Vehicle;
import com.findmyvehicle.exception.DuplicateResourceException;
import com.findmyvehicle.service.vehicle.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PreAuthorize("hasAnyRole('NORMAL', 'ADMIN')")
    @PostMapping(
            value = "/reportMissingVehicle",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Response> reportMissingVehicle(
            @RequestPart("vehicle") @Valid VehicleDto vehicleDto,
            @RequestPart(value = "imageFile", required = false) List<MultipartFile> imageFile) {

        if (vehicleService.existsByRegNumber(vehicleDto.getRegNumber())) {
            throw new DuplicateResourceException(
                    "Vehicle already exists. Missing report can be registered from the Vehicle Details page.");
        }

        vehicleService.reportMissingVehicle(vehicleDto);

        Status status = new Status();
        status.setStatus(HttpStatus.CREATED.value());
        status.setMessage("Missing report has been registered.");

        Response response = new Response();
        response.setStatus(status);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
