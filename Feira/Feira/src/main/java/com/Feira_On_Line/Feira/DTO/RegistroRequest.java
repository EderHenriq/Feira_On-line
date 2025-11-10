package com.Feira_On_Line.Feira.DTO;

import lombok.Data;

@Data
public class RegistroRequest {
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String tipoUsuario;
    private String nomeBarraca; // Opcional, somente para feirantes
}