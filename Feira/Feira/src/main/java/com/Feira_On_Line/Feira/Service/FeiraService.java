package com.Feira_On_Line.Feira.Service;

import com.Feira_On_Line.Feira.DTO.FeiraDTO;
import com.Feira_On_Line.Feira.Model.Feira;
import com.Feira_On_Line.Feira.Repository.FeiraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeiraService {

    private final FeiraRepository feiraRepository;

    public List<FeiraDTO> listarTodas() {
        return feiraRepository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public FeiraDTO buscarPorId(Long id) {
        Feira feira = feiraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feira não encontrada com ID: " + id));
        return converterParaDTO(feira);
    }

    public List<FeiraDTO> buscarPorBairro(String bairro) {
        return feiraRepository.findByBairro(bairro).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<FeiraDTO> buscarPorNome(String nome) {
        return feiraRepository.findByNomeContainingIgnoreCase(nome).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<FeiraDTO> buscarPorHorario(String horario) {
        return feiraRepository.findByHorarioContainingIgnoreCase(horario).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    private FeiraDTO converterParaDTO(Feira feira) {
        return FeiraDTO.builder()
                .id(feira.getId())
                .nome(feira.getNome())
                .descricao(feira.getDescricao())
                .localizacao(feira.getLocalizacao())
                .bairro(feira.getBairro())
                .latitude(feira.getLatitude())
                .longitude(feira.getLongitude())
                .horario(feira.getHorario())
                .horaAbertura(feira.getHoraAbertura())
                .horaFechamento(feira.getHoraFechamento())
                .comodidades(feira.getComodidades())
                .totalFeirantes(feira.getFeirantes() != null ? feira.getFeirantes().size() : 0)
                .build();
    }
}