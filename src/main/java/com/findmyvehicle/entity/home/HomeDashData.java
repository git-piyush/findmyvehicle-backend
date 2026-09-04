package com.findmyvehicle.entity.home;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_home_dash")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomeDashData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="TITLE", nullable = false, unique = true)
    private String title;

    @Column(name="HIGHLIGHTED_WORD", nullable = false, unique = true)
    private String highlightedWord;

    @Column(name="DESCRIPTION", nullable = false, unique = true)
    private String description;

    @Column(name="SEARCH_PLACE_HOLDER", nullable = false, unique = true)
    private String searchPlaceholder;

    //BOX1
    @Column(name="BOX1_VALUE", nullable = false, unique = true)
    private Long box1Value;

    @Column(name="BOX1_LABEL", nullable = false, unique = true)
    private String box1Label;

    @Column(name="BOX1_DESC", nullable = false, unique = true)
    private String box1Description;

    @Column(name="BOX1_ICON")
    private String box1Icon;

    //BOX2
    @Column(name="BOX2_VALUE", nullable = false, unique = true)
    private Long box2Value;

    @Column(name="BOX2_LABEL", nullable = false, unique = true)
    private String box2Label;

    @Column(name="BOX2_DESC", nullable = false, unique = true)
    private String box2Description;

    @Column(name="BOX2_ICON")
    private String box2Icon;

    //BOX3
    @Column(name="BOX3_VALUE", nullable = false, unique = true)
    private Long box3Value;

    @Column(name="BOX3_LABEL", nullable = false, unique = true)
    private String box3Label;

    @Column(name="BOX3_DESC", nullable = false, unique = true)
    private String box3Description;

    @Column(name="BOX3_ICON")
    private String box3Icon;

    //BOX4
    @Column(name="BOX4_VALUE", nullable = false, unique = true)
    private Long box4Value;

    @Column(name="BOX4_LABEL", nullable = false, unique = true)
    private String box4Label;

    @Column(name="BOX4_DESC", nullable = false, unique = true)
    private String box4Description;

    @Column(name="BOX4_ICON")
    private String box4Icon;
}
