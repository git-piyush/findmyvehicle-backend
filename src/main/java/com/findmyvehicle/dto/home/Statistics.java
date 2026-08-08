package com.findmyvehicle.dto.home;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Statistics {

    @Builder.Default
    private BoxData box1 = new BoxData();

    @Builder.Default
    private BoxData box2 = new BoxData();

    @Builder.Default
    private BoxData box3 = new BoxData();

    @Builder.Default
    private BoxData box4 = new BoxData();
}

