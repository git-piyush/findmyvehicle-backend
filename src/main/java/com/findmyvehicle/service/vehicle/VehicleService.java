package com.findmyvehicle.service.vehicle;

import com.findmyvehicle.dto.vehicle.VehicleDto;
import com.findmyvehicle.entity.vehicle.Vehicle;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface VehicleService {

    Boolean existsByRegNumber(String regNumber);

    Vehicle reportMissingVehicle(VehicleDto vehicleDto, List<MultipartFile> imageFile);

    List<Vehicle> getRecentMissingVehicles(Integer count);

}
