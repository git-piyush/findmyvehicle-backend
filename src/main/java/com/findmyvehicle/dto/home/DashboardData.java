package com.findmyvehicle.dto.home;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardData {

    @Builder.Default
    private HomeHeader header = new HomeHeader();

    @Builder.Default
    private Statistics statistics = new Statistics();

    @Builder.Default
    private RecentMissingVehicles recentMissingVehicles = new RecentMissingVehicles();
}

