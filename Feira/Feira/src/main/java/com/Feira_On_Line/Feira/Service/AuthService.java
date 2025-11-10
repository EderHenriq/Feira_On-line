package com.Feira_On_Line.Feira.Service;

import com.Feira_On_Line.Feira.DTO.LoginRequest;
import com.Feira_On_Line.Feira.DTO.LoginResponse;
import com.Feira_On_Line.Feira.DTO.RegistroRequest;
import com.Feira_On_Line.Feira.Model.Feirante;
import com.Feira_On_Line.Feira.Model.TipoUsuario;
import com.Feira_On_Line.Feira.Model.Usuario;
import com.Feira_On_Line.Feira.Repository.FeiranteRepository;
import com.Feira_On_Line.Feira.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final FeiranteRepository feiranteRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public LoginResponse registrar(RegistroRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        Usuario usuario = Usuario.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .senha(passwordEncoder.encode(request.getSenha()))
                .telefone(request.getTelefone())
                .tipoUsuario(TipoUsuario.valueOf(request.getTipoUsuario().toUpperCase()))
                .ativo(true)
                .build();

        usuarioRepository.save(usuario);

        if (request.getTipoUsuario().equalsIgnoreCase("FEIRANTE") && request.getNomeBarraca() != null) {
            Feirante feirante = Feirante.builder()
                    .usuario(usuario)
                    .nomeBarraca(request.getNomeBarraca())
                    .build();
            feiranteRepository.save(feirante);
        }

        String token = jwtService.gerarToken(usuario);

        return LoginResponse.builder()
                .token(token)
                .usuarioId(usuario.getId())
                .email(usuario.getEmail())
                .nome(usuario.getNome())
                .tipoUsuario(usuario.getTipoUsuario().name())
                .build();
    }

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
            throw new RuntimeException("Senha incorreta");
        }

        if (!usuario.getAtivo()) {
            throw new RuntimeException("Usuário inativo");
        }

        String token = jwtService.gerarToken(usuario);

        return LoginResponse.builder()
                .token(token)
                .usuarioId(usuario.getId())
                .email(usuario.getEmail())
                .nome(usuario.getNome())
                .tipoUsuario(usuario.getTipoUsuario().name())
                .build();
    }
}