package com.findmyvehicle.repository.vehicle;

import com.findmyvehicle.entity.vehicle.MissingDetails;
import com.findmyvehicle.enums.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissingDetailsRepository extends JpaRepository<MissingDetails, Long> {

    List<MissingDetails> findByVehicleStatus(VehicleStatus vehicleStatus);

    Long countByVehicleStatus(VehicleStatus vehicleStatus);
}
