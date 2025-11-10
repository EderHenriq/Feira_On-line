package com.Feira_On_Line.Feira.DTO;

import com.Feira_On_Line.Feira.Model.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private TipoUsuario tipoUsuario;
    private Boolean ativo;
}