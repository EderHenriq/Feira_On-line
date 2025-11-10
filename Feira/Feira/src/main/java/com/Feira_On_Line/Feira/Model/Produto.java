package com.Feira_On_Line.Feira.Model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "produtos")
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
    private Feirante feirante;

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column
    private String categoria;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column
    private String unidade;

    @Column
    private Integer estoque;

    @Column
    private String imagemUrl;

    @Column
    private Boolean organico;

    @Column
    private Boolean local;

    @Column(precision = 3, scale = 1)
    private Double avaliacao;

    @Column
    private Integer visualizacoes;

    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @Column
    private LocalDateTime atualizadoEm;

    @PrePersist
    protected void onCreate() {
        criadoEm = LocalDateTime.now();
        atualizadoEm = LocalDateTime.now();
        if (organico == null) organico = false;
        if (local == null) local = false;
        if (avaliacao == null) avaliacao = 0.0;
        if (visualizacoes == null) visualizacoes = 0;
        if (estoque == null) estoque = 0;
    }

    @PreUpdate
    protected void onUpdate() {
        atualizadoEm = LocalDateTime.now();
    }
}