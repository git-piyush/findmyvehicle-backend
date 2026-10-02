package com.findmyvehicle.repository.vehicle;

import com.findmyvehicle.entity.vehicle.Vehicle;
import com.findmyvehicle.enums.VehicleStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Boolean existsByRegNumber(String regNumber);

    List<Vehicle> findDistinctByMissingDetails_VehicleStatusOrderByCreatedDateDesc(
            VehicleStatus vehicleStatus, Pageable pageable);

        long countByReportedBy_Id(Long userId);

        @Query("SELECT COUNT(DISTINCT v.id) FROM Vehicle v JOIN v.missingDetails md "
            + "WHERE v.reportedBy.id = :userId AND md.vehicleStatus = :status")
        long countReportsByUserAndStatus(@Param("userId") Long userId,
                         @Param("status") VehicleStatus status);

}
