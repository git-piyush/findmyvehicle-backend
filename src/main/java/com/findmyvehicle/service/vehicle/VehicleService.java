package com.findmyvehicle.service.vehicle;

import com.findmyvehicle.dto.vehicle.VehicleDto;
import com.findmyvehicle.dto.vehicle.VehicleDetailsDto;
import com.findmyvehicle.entity.vehicle.Vehicle;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface VehicleService {

    Boolean existsByRegNumber(String regNumber);

    VehicleDetailsDto getVehicleDetails(String regNumber);

    Page<VehicleDetailsDto> getVehiclesReportedByCurrentUser(
            String regNumber, String model, String city, String pinCode, int page, int size);

    Page<VehicleDetailsDto> searchMissingVehicles(
            String regNumber, String model, String city, String pinCode, int page, int size);

    Vehicle reportMissingVehicle(VehicleDto vehicleDto, List<MultipartFile> imageFile);

    List<Vehicle> getRecentMissingVehicles(Integer count);

}
