package com.Feira_On_Line.Feira.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.*;

@Entity
@Table(name = "Feirante")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Feirante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private com.Feira_On_Line.Feira.Model.Usuario usuario;

    @Column(nullable = false)
    private String barracaName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private String logoUrl;

    @Column(precision = 3, scale = 1)
    private Double rating;

    @Column
    private Integer totalReviews;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<com.Feira_On_Line.Feira.Model.Produto> produto = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "Feriante_feira",
            joinColumns = @JoinColumn(name = "Feirante_id"),
            inverseJoinColumns = @JoinColumn(name = "Feira_id")
    )
    private List<com.Feira_On_Line.Feira.Model.Feira> feira = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (rating == null) rating = 0.0;
        if (totalReviews == null) totalReviews = 0;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}