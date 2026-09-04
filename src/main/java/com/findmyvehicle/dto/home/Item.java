package com.findmyvehicle.dto.home;

import jakarta.persistence.Lob;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    private String id;

    private String registrationNumber;

    private String make;

    private String model;

    private String displayName;

    private String vehicleType;

    private String status;

    private String imageUrl;

    private byte[] image;

    private Location location;

    private Date reportedAt;

    private String detailsUrl;

    private String reward;
}
