package com.Feira_On_Line.Feira.DTO;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CriarProdutoRequest {
    private String nome;
    private String descricao;
    private String categoria;
    private BigDecimal preco;
    private String unidade;
    private Integer estoque;
    private String imagemUrl;
    private Boolean organico;
    private Boolean local;
}