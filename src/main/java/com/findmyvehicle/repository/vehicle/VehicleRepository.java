package com.findmyvehicle.repository.vehicle;

import com.findmyvehicle.entity.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Boolean existsByRegNumber(String regNumber);

}
