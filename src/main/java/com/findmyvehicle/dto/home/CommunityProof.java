package com.findmyvehicle.dto.home;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityProof {
    @Builder.Default
    private String count= "";

    @Builder.Default
    private String label= "";
}
