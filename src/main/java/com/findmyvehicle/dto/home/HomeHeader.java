package com.findmyvehicle.dto.home;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomeHeader {
    private String title;

    private String highlightedWord;

    private String description;

    private String searchPlaceholder;
}
