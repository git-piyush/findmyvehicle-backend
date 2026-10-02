package com.findmyvehicle.serviceImpl.dashboard;

import com.findmyvehicle.dto.dashboard.DashboardData;
import com.findmyvehicle.entity.User;
import com.findmyvehicle.entity.vehicle.MissingDetails;
import com.findmyvehicle.entity.vehicle.Vehicle;
import com.findmyvehicle.entity.vehicle.VehicleImage;
import com.findmyvehicle.enums.VehicleStatus;
import com.findmyvehicle.repository.vehicle.VehicleRepository;
import com.findmyvehicle.service.dashboard.DashboardService;
import com.findmyvehicle.util.MultiFunctionUtility;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final VehicleRepository vehicleRepository;
    private final MultiFunctionUtility multiFunctionUtility;

    public DashboardServiceImpl(VehicleRepository vehicleRepository,
                                MultiFunctionUtility multiFunctionUtility) {
        this.vehicleRepository = vehicleRepository;
        this.multiFunctionUtility = multiFunctionUtility;
    }

    @Override
    @Transactional(readOnly = true)
    public DashboardData getDashboardData() {
        User user = multiFunctionUtility.getCurrentUser();
        Long userId = user.getId();

        DashboardData.DashboardSummary summary = new DashboardData.DashboardSummary(
                vehicleRepository.countByReportedBy_Id(userId),
                vehicleRepository.countReportsByUserAndStatus(userId, VehicleStatus.FOUND),
                vehicleRepository.countReportsByUserAndStatus(userId, VehicleStatus.MISSING),
                vehicleRepository.countReportsByUserAndStatus(userId, VehicleStatus.CLOSED));

        List<DashboardData.DashboardVehicle> recentVehicles = vehicleRepository
                .findDistinctByMissingDetails_VehicleStatusOrderByCreatedDateDesc(
                        VehicleStatus.MISSING, PageRequest.of(0, 3))
                .stream()
                .map(this::toDashboardVehicle)
                .toList();

        return new DashboardData(
                new DashboardData.DashboardUser(
                        user.getId(), user.getName(), user.getEmail(), user.getProfilePic()),
                summary,
                new DashboardData.DashboardActivity(0, 0),
                recentVehicles);
    }

    private DashboardData.DashboardVehicle toDashboardVehicle(Vehicle vehicle) {
        MissingDetails latestDetails = vehicle.getMissingDetails().stream()
                .max(Comparator.comparing(MissingDetails::getId))
                .orElse(null);

        String location = null;
        String reportedAt = null;
        if (latestDetails != null) {
            String city = latestDetails.getCity();
            String state = latestDetails.getState() == null ? null : latestDetails.getState().name();
            location = city == null ? state : state == null ? city : city + ", " + state;
            if (latestDetails.getMissingDate() != null) {
                reportedAt = LocalDateTime.of(
                        latestDetails.getMissingDate(),
                        Objects.requireNonNullElse(latestDetails.getMissingTime(), LocalTime.MIDNIGHT))
                        .toString();
            }
        }

        String image = vehicle.getImages().stream()
                .map(VehicleImage::getImageUrl)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);

        return new DashboardData.DashboardVehicle(
                vehicle.getId(),
                vehicle.getVehicleModel(),
                vehicle.getRegNumber(),
                location,
                reportedAt,
                image,
                vehicle.getChassisNumber(),
                vehicle.getEngineNumber(),
                vehicle.getColor(),
                latestDetails == null ? null : latestDetails.getDescription(),
                latestDetails == null || latestDetails.getVehicleStatus() == null
                        ? null : latestDetails.getVehicleStatus().name());
    }
}