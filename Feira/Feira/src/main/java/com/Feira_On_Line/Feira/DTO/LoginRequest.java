package com.Feira_On_Line.Feira.DTO;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String senha;
}