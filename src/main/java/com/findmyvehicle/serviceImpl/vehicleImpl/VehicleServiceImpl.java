package com.findmyvehicle.serviceImpl.vehicleImpl;

import com.findmyvehicle.dto.vehicle.VehicleDto;
import com.findmyvehicle.entity.vehicle.MissingDetails;
import com.findmyvehicle.entity.vehicle.Vehicle;
import com.findmyvehicle.repository.vehicle.VehicleRepository;
import com.findmyvehicle.service.vehicle.VehicleService;
import com.findmyvehicle.util.MultiFunctionUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MultiFunctionUtility multiFunctionUtility;

    @Override
    public Boolean existsByRegNumber(String regNumber) {
        return vehicleRepository.existsByRegNumber(regNumber);
    }

    @Override
    @Transactional
    public Vehicle reportMissingVehicle(VehicleDto vehicleDto) {

        Vehicle vehicle = mapper.map(vehicleDto, Vehicle.class);

        MissingDetails missingDetails =
                mapper.map(vehicleDto.getMissingDetails(), MissingDetails.class);

        missingDetails.setVehicle(vehicle);

        vehicle.setMissingDetails(List.of(missingDetails));
        vehicle.setReportedBy(multiFunctionUtility.getCurrentUser());
        return vehicleRepository.save(vehicle);
    }
}
