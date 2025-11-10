package com.Feira_On_Line.Feira.Service;

import com.Feira_On_Line.Feira.DTO.AtualizarFeiranteRequest;
import com.Feira_On_Line.Feira.DTO.FeiranteDTO;
import com.Feira_On_Line.Feira.Model.Feirante;
import com.Feira_On_Line.Feira.Repository.FeiranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeiranteService {

    private final FeiranteRepository feiranteRepository;

    public List<FeiranteDTO> listarTodos() {
        return feiranteRepository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public FeiranteDTO buscarPorId(Long id) {
        Feirante feirante = feiranteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feirante não encontrado com ID: " + id));
        return converterParaDTO(feirante);
    }

    public FeiranteDTO buscarPorUsuarioId(Long usuarioId) {
        Feirante feirante = feiranteRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Feirante não encontrado para usuário ID: " + usuarioId));
        return converterParaDTO(feirante);
    }

    public FeiranteDTO atualizar(Long id, AtualizarFeiranteRequest request) {
        Feirante feirante = feiranteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feirante não encontrado com ID: " + id));

        if (request.getNomeBarraca() != null) {
            feirante.setNomeBarraca(request.getNomeBarraca());
        }
        if (request.getDescricao() != null) {
            feirante.setDescricao(request.getDescricao());
        }
        if (request.getLogoUrl() != null) {
            feirante.setLogoUrl(request.getLogoUrl());
        }

        feiranteRepository.save(feirante);
        return converterParaDTO(feirante);
    }

    private FeiranteDTO converterParaDTO(Feirante feirante) {
        return FeiranteDTO.builder()
                .id(feirante.getId())
                .nomeBarraca(feirante.getNomeBarraca())
                .descricao(feirante.getDescricao())
                .logoUrl(feirante.getLogoUrl())
                .avaliacao(feirante.getAvaliacao())
                .totalAvaliacoes(feirante.getTotalAvaliacoes())
                .nomeDono(feirante.getUsuario().getNome())
                .emailDono(feirante.getUsuario().getEmail())
                .telefoneDono(feirante.getUsuario().getTelefone())
                .build();
    }
}