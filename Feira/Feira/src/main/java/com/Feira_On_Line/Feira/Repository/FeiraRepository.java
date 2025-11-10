package com.Feira_On_Line.Feira.Repository;

import com.Feira_On_Line.Feira.Model.Feira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Indica ao Spring que esta é uma interface de repositório
public interface FeiraRepository extends JpaRepository<Feira, Long> {

    // O Spring Data JPA cria a consulta automaticamente pelo nome do método

    // Método para buscar feiras por dia da semana
    List<Feira> findByDiaDaSemanaContainingIgnoreCase(String diaDaSemana);

    // Método para buscar feiras por bairro
    List<Feira> findByBairroContainingIgnoreCase(String bairro);

}