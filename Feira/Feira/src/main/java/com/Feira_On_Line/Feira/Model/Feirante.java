// ============================================
// ENTIDADE: Feirante.java
// ============================================
package com.Feira_On_Line.Feira.Model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "feirantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feirante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    @Column(nullable = false)
    private String nomeBarraca;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column
    private String logoUrl;

    @Column(precision = 3, scale = 1)
    private Double avaliacao;

    @Column
    private Integer totalAvaliacoes;

    @OneToMany(mappedBy = "feirante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produto> produtos = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "feirante_feiras",
            joinColumns = @JoinColumn(name = "feirante_id"),
            inverseJoinColumns = @JoinColumn(name = "feira_id")
    )
    private List<Feira> feiras = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @Column
    private LocalDateTime atualizadoEm;

    @PrePersist
    protected void onCreate() {
        criadoEm = LocalDateTime.now();
        atualizadoEm = LocalDateTime.now();
        if (avaliacao == null) avaliacao = 0.0;
        if (totalAvaliacoes == null) totalAvaliacoes = 0;
    }

    @PreUpdate
    protected void onUpdate() {
        atualizadoEm = LocalDateTime.now();
    }
}