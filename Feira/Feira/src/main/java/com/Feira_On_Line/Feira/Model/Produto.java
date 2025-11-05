package com.Feira_On_Line.Feira.Model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Produtos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feirante_id", nullable = false)

    private com.Feira_On_Line.Feira.Model.Feirante feirante;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private String category;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column
    private String unit;

    @Column
    private Integer stock;

    @Column
    private String imageUrl;

    @Column
    private Boolean organic;

    @Column
    private Boolean local;

    @Column(precision = 3, scale = 1)
    private Double rating;

    @Column
    private Integer views;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (organic == null) organic = false;
        if (local == null) local = false;
        if (rating == null) rating = 0.0;
        if (views == null) views = 0;
        if (stock == null) stock = 0;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}