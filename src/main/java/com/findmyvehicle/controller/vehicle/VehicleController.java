package com.findmyvehicle.controller.vehicle;

import com.findmyvehicle.dto.Response;
import com.findmyvehicle.dto.Status;
import com.findmyvehicle.dto.vehicle.VehicleDetailsDto;
import com.findmyvehicle.dto.vehicle.VehicleDto;
import com.findmyvehicle.exception.DuplicateResourceException;
import com.findmyvehicle.service.vehicle.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/vehicle/{regNumber}")
    public ResponseEntity<Response<VehicleDetailsDto>> getVehicleDetails(
            @PathVariable String regNumber) {
        Response<VehicleDetailsDto> response = new Response<>();
        Status status = new Status();
        status.setStatus(HttpStatus.OK.value());
        status.setMessage("Vehicle details retrieved.");
        response.setStatus(status);
        response.setData(vehicleService.getVehicleDetails(regNumber));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vehicles/search")
    public ResponseEntity<Response<Page<VehicleDetailsDto>>> searchMissingVehicles(
            @RequestParam(required = false) String regNumber,
            @RequestParam(required = false) String model,
            @RequestParam(name = "missingCity", required = false) String city,
            @RequestParam(required = false) String pinCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        if (page < 0 || size < 1 || size > 100) {
            Status status = new Status();
            status.setStatus(HttpStatus.BAD_REQUEST.value());
            status.setMessage("Page must be non-negative and size must be between 1 and 100.");
            Response<Page<VehicleDetailsDto>> response = new Response<>();
            response.setStatus(status);
            return ResponseEntity.badRequest().body(response);
        }

        Response<Page<VehicleDetailsDto>> response = new Response<>();
        Status status = new Status();
        status.setStatus(HttpStatus.OK.value());
        status.setMessage("Missing vehicles retrieved.");
        response.setStatus(status);
        response.setData(vehicleService.searchMissingVehicles(regNumber, model, city, pinCode, page, size));
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('NORMAL', 'ADMIN')")
    @PostMapping(value = "/reportMissingVehicle",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response> reportMissingVehicle(@RequestPart("vehicle") @Valid VehicleDto vehicleDto,
            @RequestPart(value = "imageFile", required = false) List<MultipartFile> imageFile) {

        if (vehicleService.existsByRegNumber(vehicleDto.getRegNumber())) {
            throw new DuplicateResourceException(
                    "Vehicle already exists. Missing report can be registered from the Vehicle Details page.");
        }
        vehicleService.reportMissingVehicle(vehicleDto,imageFile);
        Status status = new Status();
        status.setStatus(HttpStatus.CREATED.value());
        status.setMessage("Missing report has been registered.");
        Response response = new Response();
        response.setStatus(status);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
