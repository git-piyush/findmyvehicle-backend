package com.findmyvehicle.repository.vehicle;

import com.findmyvehicle.entity.vehicle.MissingDetails;
import com.findmyvehicle.enums.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissingDetailsRepository extends JpaRepository<MissingDetails, Long> {

    List<MissingDetails> findByVehicleStatus(VehicleStatus vehicleStatus);

    Long countByVehicleStatus(VehicleStatus vehicleStatus);

    @Query("SELECT COUNT(DISTINCT md.state) FROM MissingDetails md WHERE md.state IS NOT NULL")
    long countDistinctReportedStates();
}
