package com.Feira_On_Line.Feira.Controller;

import com.Feira_On_Line.Feira.DTO.LoginRequest;
import com.Feira_On_Line.Feira.DTO.LoginResponse;
import com.Feira_On_Line.Feira.DTO.RegistroRequest;
import com.Feira_On_Line.Feira.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registrar")
    public ResponseEntity<LoginResponse> registrar(@RequestBody RegistroRequest request) {
        LoginResponse response = authService.registrar(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}