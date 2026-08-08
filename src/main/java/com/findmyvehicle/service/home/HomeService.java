package com.findmyvehicle.service.home;

import com.findmyvehicle.dto.home.DashboardData;
import org.springframework.stereotype.Service;

@Service
public interface HomeService {

    void refreshHomeDashboardData();

    DashboardData getDashboardData();

}
