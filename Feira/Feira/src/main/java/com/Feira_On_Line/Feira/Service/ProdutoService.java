package com.Feira_On_Line.Feira.Service;

import com.Feira_On_Line.Feira.DTO.CriarProdutoRequest;
import com.Feira_On_Line.Feira.DTO.ProdutoDTO;
import com.Feira_On_Line.Feira.Model.Feirante;
import com.Feira_On_Line.Feira.Model.Produto;
import com.Feira_On_Line.Feira.Repository.FeiranteRepository;
import com.Feira_On_Line.Feira.Repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final FeiranteRepository feiranteRepository;

    public List<ProdutoDTO> listarTodos() {
        return produtoRepository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public ProdutoDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));

        // Incrementar visualizações
        produto.setVisualizacoes(produto.getVisualizacoes() + 1);
        produtoRepository.save(produto);

        return converterParaDTO(produto);
    }

    public List<ProdutoDTO> buscarPorFeirante(Long feiranteId) {
        return produtoRepository.findByFeiranteId(feiranteId).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<ProdutoDTO> buscarPorCategoria(String categoria) {
        return produtoRepository.findByCategoria(categoria).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<ProdutoDTO> buscarOrganicos() {
        return produtoRepository.findByOrganicoTrue().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<ProdutoDTO> buscarMaisVistos() {
        return produtoRepository.findMaisVistos().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public ProdutoDTO criar(Long feiranteId, CriarProdutoRequest request) {
        Feirante feirante = feiranteRepository.findById(feiranteId)
                .orElseThrow(() -> new RuntimeException("Feirante não encontrado com ID: " + feiranteId));

        Produto produto = Produto.builder()
                .feirante(feirante)
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .categoria(request.getCategoria())
                .preco(request.getPreco())
                .unidade(request.getUnidade())
                .estoque(request.getEstoque())
                .imagemUrl(request.getImagemUrl())
                .organico(request.getOrganico())
                .local(request.getLocal())
                .build();

        produtoRepository.save(produto);
        return converterParaDTO(produto);
    }

    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com ID: " + id);
        }
        produtoRepository.deleteById(id);
    }

    private ProdutoDTO converterParaDTO(Produto produto) {
        return ProdutoDTO.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .categoria(produto.getCategoria())
                .preco(produto.getPreco())
                .unidade(produto.getUnidade())
                .estoque(produto.getEstoque())
                .imagemUrl(produto.getImagemUrl())
                .organico(produto.getOrganico())
                .local(produto.getLocal())
                .avaliacao(produto.getAvaliacao())
                .visualizacoes(produto.getVisualizacoes())
                .feiranteId(produto.getFeirante().getId())
                .nomeFeirante(produto.getFeirante().getNomeBarraca())
                .build();
    }
}