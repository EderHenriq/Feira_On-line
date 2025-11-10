package com.Feira_On_Line.Feira.Repository;

import com.Feira_On_Line.Feira.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByFeiranteId(Long feiranteId);
    List<Produto> findByNomeContainingIgnoreCase(String nome);
    List<Produto> findByCategoria(String categoria);
    List<Produto> findByOrganicoTrue();
    List<Produto> findByLocalTrue();

    @Query("SELECT p FROM Produto p WHERE p.organico = true AND p.local = true")
    List<Produto> findByOrganicoELocal();

    @Query("SELECT p FROM Produto p ORDER BY p.visualizacoes DESC")
    List<Produto> findMaisVistos();
}