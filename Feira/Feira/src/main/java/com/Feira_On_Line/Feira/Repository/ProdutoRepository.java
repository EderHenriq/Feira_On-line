// ProductRepository.java
package com.Feira_On_Line.Feira.Repository;

import com.Feira_On_Line.Feira.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByFeiranteId(Long feiranteId);
    List<Produto> findByNameContainingIgnoreCase(String name);
    List<Produto> findByCategoria(String categoria);
    List<Produto> getProdutoById(Long id);
}
