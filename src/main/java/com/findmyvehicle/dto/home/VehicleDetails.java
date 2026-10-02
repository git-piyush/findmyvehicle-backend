package com.findmyvehicle.dto.home;

import lombok.*;

import java.util.Date;
import java.util.List;

import com.findmyvehicle.enums.VehicleCompany;
import com.findmyvehicle.enums.VehicleStatus;
import com.findmyvehicle.enums.VehicleType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDetails {
    private String id;

    private String registrationNumber;

    private VehicleCompany brand;

    private String model;

    private VehicleType vehicleType;

    private String displayName;

    private VehicleStatus status;

    private List<String> imageUrls;

    private Location missingLocation;

    private Date missigngDateTime;

    private String detailUrl;

    private Reward reward;
}
