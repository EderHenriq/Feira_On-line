package com.Feira_On_Line.Feira.Controller;

import com.Feira_On_Line.Feira.DTO.AtualizarFeiranteRequest;
import com.Feira_On_Line.Feira.DTO.FeiranteDTO;
import com.Feira_On_Line.Feira.Service.FeiranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/feirantes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FeiranteController {

    private final FeiranteService feiranteService;

    @GetMapping
    public ResponseEntity<List<FeiranteDTO>> listarTodos() {
        List<FeiranteDTO> feirantes = feiranteService.listarTodos();
        return ResponseEntity.ok(feirantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeiranteDTO> buscarPorId(@PathVariable Long id) {
        FeiranteDTO feirante = feiranteService.buscarPorId(id);
        return ResponseEntity.ok(feirante);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<FeiranteDTO> buscarPorUsuarioId(@PathVariable Long usuarioId) {
        FeiranteDTO feirante = feiranteService.buscarPorUsuarioId(usuarioId);
        return ResponseEntity.ok(feirante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeiranteDTO> atualizar(
            @PathVariable Long id,
            @RequestBody AtualizarFeiranteRequest request
    ) {
        FeiranteDTO feiranteAtualizado = feiranteService.atualizar(id, request);
        return ResponseEntity.ok(feiranteAtualizado);
    }
}