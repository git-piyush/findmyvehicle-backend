package com.findmyvehicle.dto.vehicle;

import com.findmyvehicle.enums.VehicleCompany;
import com.findmyvehicle.enums.VehicleStatus;
import com.findmyvehicle.enums.VehicleType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDto {

        @NotBlank(message = "Registration number is required.")
        private String regNumber;

        @NotBlank(message = "Chassis number is required.")
        private String chassisNumber;

        @NotBlank(message = "Provide the vehicle owner.")
        private String owner;

        @NotBlank(message = "Provide the vehicle owner.")
        private String ownerMobile;

        @NotBlank(message = "Provide the vehicle owner.")
        private String ownerEmail;

        private String color;

        @NotNull(message = "Vehicle type is required.")
        private VehicleType type;

        @NotNull(message = "Vehicle company is required.")
        private VehicleCompany vehicleCompany;

        private VehicleStatus vehicleStatus;

        private String vehicleModel;

        private String engineNumber;

        @Valid
        @NotNull(message = "Missing details are required.")
        private MissingDetailsDto missingDetails;

}
