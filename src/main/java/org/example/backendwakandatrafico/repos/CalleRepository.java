package org.example.backendwakandatrafico.repos;


import org.example.backendwakandatrafico.domain.Calle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalleRepository extends JpaRepository<Calle, Long> {
    List<Calle> findAllByOrderByParkingsDisponiblesDesc(); // Ordena por parkings libres
}
