package com.Feira_On_Line.Feira.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private Long usuarioId;
    private String email;
    private String nome;
    private String tipoUsuario;
}