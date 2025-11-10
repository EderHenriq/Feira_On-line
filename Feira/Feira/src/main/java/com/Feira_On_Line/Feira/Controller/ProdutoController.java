package com.Feira_On_Line.Feira.Controller;

import com.Feira_On_Line.Feira.DTO.CriarProdutoRequest;
import com.Feira_On_Line.Feira.DTO.ProdutoDTO;
import com.Feira_On_Line.Feira.Service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarTodos() {
        List<ProdutoDTO> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long id) {
        ProdutoDTO produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/feirante/{feiranteId}")
    public ResponseEntity<List<ProdutoDTO>> buscarPorFeirante(@PathVariable Long feiranteId) {
        List<ProdutoDTO> produtos = produtoService.buscarPorFeirante(feiranteId);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/buscar/categoria")
    public ResponseEntity<List<ProdutoDTO>> buscarPorCategoria(@RequestParam String categoria) {
        List<ProdutoDTO> produtos = produtoService.buscarPorCategoria(categoria);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/organicos")
    public ResponseEntity<List<ProdutoDTO>> buscarOrganicos() {
        List<ProdutoDTO> produtos = produtoService.buscarOrganicos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/mais-vistos")
    public ResponseEntity<List<ProdutoDTO>> buscarMaisVistos() {
        List<ProdutoDTO> produtos = produtoService.buscarMaisVistos();
        return ResponseEntity.ok(produtos);
    }

    @PostMapping("/feirante/{feiranteId}")
    public ResponseEntity<ProdutoDTO> criar(
            @PathVariable Long feiranteId,
            @RequestBody CriarProdutoRequest request
    ) {
        ProdutoDTO produtoCriado = produtoService.criar(feiranteId, request);
        return ResponseEntity.ok(produtoCriado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}