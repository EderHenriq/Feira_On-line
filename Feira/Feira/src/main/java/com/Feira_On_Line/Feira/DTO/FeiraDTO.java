package com.Feira_On_Line.Feira.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeiraDTO {
    private Long id;
    private String nome;
    private String descricao;
    private String localizacao;
    private String bairro;
    private Double latitude;
    private Double longitude;
    private String horario;
    private LocalTime horaAbertura;
    private LocalTime horaFechamento;
    private String comodidades;
    private Integer totalFeirantes;
}
