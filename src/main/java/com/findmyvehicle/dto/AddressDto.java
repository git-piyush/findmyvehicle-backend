package com.findmyvehicle.dto;

import com.findmyvehicle.entity.vehicle.State;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor   // generates public Status(int, String)
@NoArgsConstructor
public class AddressDto {

    private Long id;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private State state;

    private String pinCode;

    private String country;
}
