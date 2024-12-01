package org.example.backendwakandatrafico.service;



import org.example.backendwakandatrafico.domain.Calle;
import org.example.backendwakandatrafico.repos.CalleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalleService {
    private final CalleRepository calleRepository;

    public CalleService(CalleRepository calleRepository) {
        this.calleRepository = calleRepository;
    }

    public List<Calle> listarCalles() {
        return calleRepository.findAll();
    }

    public Calle actualizarParkings(Long id, int parkingsDisponibles) {
        Calle calle = calleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calle no encontrada"));
        calle.setParkingsDisponibles(parkingsDisponibles);
        return calleRepository.save(calle);
    }

    public Calle buscarCalleConMasParkings() {
        return calleRepository.findAllByOrderByParkingsDisponiblesDesc().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No hay calles disponibles"));
    }
}

