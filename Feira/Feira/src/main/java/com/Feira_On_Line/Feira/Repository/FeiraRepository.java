package com.Feira_On_Line.Feira.Repository;

import com.Feira_On_Line.Feira.Model.Feira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FeiraRepository extends JpaRepository<Feira, Long> {
    List<Feira> findByBairro(String bairro);
    List<Feira> findByNomeContainingIgnoreCase(String nome);
    List<Feira> findByHorarioContainingIgnoreCase(String horario);
}