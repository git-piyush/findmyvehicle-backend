package com.findmyvehicle.serviceImpl.home;

import com.findmyvehicle.dto.home.DashboardData;
import com.findmyvehicle.entity.home.HomeDashData;
import com.findmyvehicle.entity.home.Statistics;
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

import java.util.ArrayList;
import java.util.List;

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
            List<Statistics> item = new ArrayList<Statistics>();

            log.debug("Existing dashboard record found with ID: {}", homeDashDataDB.getId());
            homeDashData.setId(homeDashDataDB.getId());
            homeDashData.setEyebrow("India's community recovery network");
            homeDashData.setTitle("Find Your Missing Vehicle Faster.");
            homeDashData.setHighlightedWord("Faster");
            homeDashData.setDescription("A community platform that connects vehicle owners, citizens and authorities to help recover missing or stolen vehicles.");
            homeDashData.setSearchPlaceholder("Search Reg. Number");
            homeDashData.setReportMissingUrl("/report");
            homeDashData.setSearchVehiclesUrl("/search");

            Statistics item1 = new Statistics();
            item1.setKey("vehiclesReported");
            item1.setValue("1");
            item1.setLabel("Vehicles Reported");
            item1.setDescription("Across India");
            item1.setIcon("directions_car");
            item1.setHomeDashData(homeDashData);
            item.add(item1);

            Statistics item2 = new Statistics();
            item2.setKey("vehiclesRecovered");
            item2.setValue("0");
            item2.setLabel("Vehicles Recovered");
            item2.setDescription("Successfully Recovered");
            item2.setIcon("verified_user");
            item.add(item2);

            Statistics item3 = new Statistics();
            item3.setKey("registeredUsers");
            item3.setValue("4");
            item3.setLabel("Registered Users");
            item3.setDescription("Trusted Community");
            item3.setIcon("group");
            item3.setHomeDashData(homeDashData);
            item.add(item3);

            Statistics item4 = new Statistics();
            item4.setKey("statesCovered");
            item4.setValue("1");
            item4.setLabel("States Covered");
            item4.setDescription("Pan India Coverage");
            item4.setIcon("location_on");
            item4.setHomeDashData(homeDashData);
            item.add(item4);

            homeDashData.setStatistics(item);
            log.info("Dashboard data updated successfully.");
        } else {
            log.warn("No existing dashboard record found. Creating a new one...");
            List<Statistics> item = new ArrayList<Statistics>();

            homeDashData.setEyebrow("India's community recovery network");
            homeDashData.setTitle("Find Your Missing Vehicle Faster.");
            homeDashData.setHighlightedWord("Faster");
            homeDashData.setDescription("A community platform that connects vehicle owners, citizens and authorities to help recover missing or stolen vehicles.");
            homeDashData.setSearchPlaceholder("Search Reg. Number");
            homeDashData.setReportMissingUrl("/report");
            homeDashData.setSearchVehiclesUrl("/search");

            Statistics item1 = new Statistics();
            item1.setKey("vehiclesReported");
            item1.setValue("1");
            item1.setLabel("Vehicles Reported");
            item1.setDescription("Across India");
            item1.setIcon("directions_car");
            item1.setHomeDashData(homeDashData);
            item.add(item1);

            Statistics item2 = new Statistics();
            item2.setKey("vehiclesRecovered");
            item2.setValue("0");
            item2.setLabel("Vehicles Recovered");
            item2.setDescription("Successfully Recovered");
            item2.setIcon("verified_user");
            item2.setHomeDashData(homeDashData);
            item.add(item2);

            Statistics item3 = new Statistics();
            item3.setKey("registeredUsers");
            item3.setValue("4");
            item3.setLabel("Registered Users");
            item3.setDescription("Trusted Community");
            item3.setIcon("group");
            item3.setHomeDashData(homeDashData);
            item.add(item3);

            Statistics item4 = new Statistics();
            item4.setKey("statesCovered");
            item4.setValue("1");
            item4.setLabel("States Covered");
            item4.setDescription("Pan India Coverage");
            item4.setIcon("location_on");
            item4.setHomeDashData(homeDashData);
            item.add(item4);

            homeDashData.setStatistics(item);
            log.info("New dashboard record created successfully.");
        }
        homeRepository.save(homeDashData);
        log.info("Dashboard data updated successfully.");
    }

    @Override
    public DashboardData getDashboardData() {

        HomeDashData homeDashDataDB = homeRepository.findFirstByOrderByIdAsc();

        DashboardData dashboardData = mapperService.homeDashDataToDashboardDate(homeDashDataDB);

        return dashboardData;
    }
}
