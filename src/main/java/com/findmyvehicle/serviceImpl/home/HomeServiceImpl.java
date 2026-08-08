package com.findmyvehicle.serviceImpl.home;

import com.findmyvehicle.dto.home.DashboardData;
import com.findmyvehicle.entity.home.HomeDashData;
import com.findmyvehicle.enums.VehicleStatus;
import com.findmyvehicle.repository.UserRepository;
import com.findmyvehicle.repository.home.HomeRepository;
import com.findmyvehicle.repository.vehicle.MissingDetailsRepository;
import com.findmyvehicle.repository.vehicle.VehicleRepository;
import com.findmyvehicle.service.home.HomeService;
import com.findmyvehicle.util.MapperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HomeServiceImpl implements HomeService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HomeRepository homeRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private MissingDetailsRepository missingDetailsRepository;

    @Autowired
    private MapperService mapperService;

    @Override
    public void refreshHomeDashboardData() {
        log.info("Refreshing Home Dashboard Data...");

        HomeDashData homeDashData = new HomeDashData();
        HomeDashData homeDashDataDB = homeRepository.findFirstByOrderByIdAsc();

        if (homeDashDataDB != null) {
            log.debug("Existing dashboard record found with ID: {}", homeDashDataDB.getId());

            Long totalVehicleReported = vehicleRepository.count();
            log.info("Total Vehicles Reported: {}", totalVehicleReported);
            homeDashDataDB.setBox1Value(totalVehicleReported);

            Long totalMissingVehicle = missingDetailsRepository.countByVehicleStatus(VehicleStatus.MISSING);
            log.info("Total Missing Vehicles: {}", totalMissingVehicle);
            homeDashDataDB.setBox2Value(totalMissingVehicle);

            Long totalRecoveredVehicle = missingDetailsRepository.countByVehicleStatus(VehicleStatus.FOUND);
            log.info("Total Recovered Vehicles: {}", totalRecoveredVehicle);
            homeDashDataDB.setBox3Value(totalRecoveredVehicle);

            Long totalUser = userRepository.count();
            log.info("Total Users: {}", totalUser);
            homeDashDataDB.setBox4Value(totalUser);

            homeRepository.save(homeDashDataDB);
            log.info("Dashboard data updated successfully.");
        } else {
            log.warn("No existing dashboard record found. Creating a new one...");

            homeDashData.setTitle("Find Your Missing Vehicle Faster.");
            homeDashData.setHighlightedWord("Faster");
            homeDashData.setDescription("A community platform that connects vehicle owners, citizens and authorities to help recover missing or stolen vehicles.");
            homeDashData.setSearchPlaceholder("Search Reg. Number");

            Long totalVehicleReported = vehicleRepository.count();
            log.info("Total Vehicles Reported: {}", totalVehicleReported);
            homeDashData.setBox1Value(totalVehicleReported);
            homeDashData.setBox1Label("Total Vehicles Registered");
            homeDashData.setBox1Description("Across India");
            homeDashData.setBox1Icon("directions_car");

            Long totalMissingVehicle = missingDetailsRepository.countByVehicleStatus(VehicleStatus.MISSING);
            log.info("Total Missing Vehicles: {}", totalMissingVehicle);
            homeDashData.setBox2Value(totalMissingVehicle);
            homeDashData.setBox2Label("Total Missing Vehicle Reported.");
            homeDashData.setBox2Description("We are here to help you in spreading the missing vehicle details in local community.");
            homeDashData.setBox2Icon("directions_car");

            Long totalRecoveredVehicle = missingDetailsRepository.countByVehicleStatus(VehicleStatus.FOUND);
            log.info("Total Recovered Vehicles: {}", totalRecoveredVehicle);
            homeDashData.setBox3Value(totalRecoveredVehicle);
            homeDashData.setBox3Label("Total Recovered Vehicle.");
            homeDashData.setBox3Description("Keep your vehicle safe. Have a safe drive.");
            homeDashData.setBox3Icon("directions_car");

            Long totalUser = userRepository.count();
            log.info("Total Users: {}", totalUser);
            homeDashData.setBox4Value(totalUser);
            homeDashData.setBox4Label("Registered Users.");
            homeDashData.setBox4Description("Trusted Community.");
            homeDashData.setBox4Icon("group");

            homeRepository.save(homeDashData);
            log.info("New dashboard record created successfully.");
        }
    }

    @Override
    public DashboardData getDashboardData() {

        HomeDashData homeDashDataDB = homeRepository.findFirstByOrderByIdAsc();
        DashboardData dashboardData = mapperService.homeDashDataToDashboardDate(homeDashDataDB);

        return dashboardData;
    }
}
