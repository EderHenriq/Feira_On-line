package com.Feira_On_Line.Feira.Repository;

import com.Feira_On_Line.Feira.Model.Feirante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FeiranteRepository extends JpaRepository<Feirante, Long> {

    Optional<Feirante> findByUsuarioId(Long usuarioId);
}
