package com.findmyvehicle.dto.home;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoxData {
    private Long value;

    private String label;

    private String description;

    private String icon;
}
