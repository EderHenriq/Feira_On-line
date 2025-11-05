package com.Feira_On_Line.Feira.Model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "feiras")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String location;

    @Column
    private String neighborhood;

    @Column(precision = 10, scale = 8)
    private Double latitude;

    @Column(precision = 11, scale = 8)
    private Double longitude;

    @Column
    private String schedule;

    @Column
    private LocalTime openingTime;

    @Column
    private LocalTime closingTime;

    @Column(columnDefinition = "JSON")
    private String amenities;

    @ManyToMany(mappedBy = "fairs")
    private List<com.Feira_On_Line.Feira.Model.Feira> feiras = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
