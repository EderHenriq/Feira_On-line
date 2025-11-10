package com.Feira_On_Line.Feira.Controller;

import com.Feira_On_Line.Feira.DTO.FeiraDTO;
import com.Feira_On_Line.Feira.Service.FeiraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/feiras")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FeiraController {

    private final FeiraService feiraService;

    @GetMapping
    public ResponseEntity<List<FeiraDTO>> listarTodas() {
        List<FeiraDTO> feiras = feiraService.listarTodas();
        return ResponseEntity.ok(feiras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeiraDTO> buscarPorId(@PathVariable Long id) {
        FeiraDTO feira = feiraService.buscarPorId(id);
        return ResponseEntity.ok(feira);
    }

    @GetMapping("/buscar/bairro")
    public ResponseEntity<List<FeiraDTO>> buscarPorBairro(@RequestParam String bairro) {
        List<FeiraDTO> feiras = feiraService.buscarPorBairro(bairro);
        return ResponseEntity.ok(feiras);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<FeiraDTO>> buscarPorNome(@RequestParam String nome) {
        List<FeiraDTO> feiras = feiraService.buscarPorNome(nome);
        return ResponseEntity.ok(feiras);
    }

    @GetMapping("/buscar/horario")
    public ResponseEntity<List<FeiraDTO>> buscarPorHorario(@RequestParam String horario) {
        List<FeiraDTO> feiras = feiraService.buscarPorHorario(horario);
        return ResponseEntity.ok(feiras);
    }
}