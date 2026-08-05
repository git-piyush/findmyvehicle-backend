package com.findmyvehicle.dto.home;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Data {
    private HomeHeader header;

    private Statistics statistics;

    private RecentMissingVehicles recentMissingVehicles;


}
