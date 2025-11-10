package com.Feira_On_Line.Feira.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeiranteDTO {
    private Long id;
    private String nomeBarraca;
    private String descricao;
    private String logoUrl;
    private Double avaliacao;
    private Integer totalAvaliacoes;
    private String nomeDono;
    private String emailDono;
    private String telefoneDono;
}
