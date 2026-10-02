package com.findmyvehicle.entity.home;

import com.findmyvehicle.entity.vehicle.Vehicle;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

@Entity
@Table(name = "tbl_statistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="key")
    private String key;

    @Column(name="value")
    private String value;

    @Column(name="label")
    private String label;

    @Column(name="description")
    private String description;

    @Column(name="icon")
    private String icon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_dash_id", nullable = false)
    private HomeDashData homeDashData;
}
