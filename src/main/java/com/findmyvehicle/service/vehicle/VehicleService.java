package com.findmyvehicle.service.vehicle;

import com.findmyvehicle.dto.vehicle.VehicleDto;
import com.findmyvehicle.entity.vehicle.Vehicle;
import org.springframework.stereotype.Service;

@Service
public interface VehicleService {

    Boolean existsByRegNumber(String regNumber);

    Vehicle reportMissingVehicle(VehicleDto vehicleDto);

}
