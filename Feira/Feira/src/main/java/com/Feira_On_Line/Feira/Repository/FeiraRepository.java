// FairRepository.java
package com.Feira_On_Line.Feira.Repository;

import com.Feira_On_Line.Feira.Model.Feira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FeiraRepository extends JpaRepository<Feira, Long> {
    List<Feira> findByNeighborhood(String neighborhood);
    List<Feira> findByNameContainingIgnoreCase(String name);
}
