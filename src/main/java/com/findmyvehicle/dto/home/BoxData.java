package com.findmyvehicle.dto.home;

import lombok.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoxData {

    @Builder.Default
    private Long value = 0L;

    @Builder.Default
    private String label = "";

    @Builder.Default
    private String description = "";

    @Builder.Default
    private String icon = "";
}

