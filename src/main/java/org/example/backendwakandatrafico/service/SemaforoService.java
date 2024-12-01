package org.example.backendwakandatrafico.service;



import org.example.backendwakandatrafico.domain.Semaforo;
import org.example.backendwakandatrafico.repos.SemaforoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemaforoService {
    private final SemaforoRepository semaforoRepository;

    public SemaforoService(SemaforoRepository semaforoRepository) {
        this.semaforoRepository = semaforoRepository;
    }

    public List<Semaforo> listarSemaforos() {
        return semaforoRepository.findAll();
    }

    public Semaforo actualizarEstadoSemaforo(Long id, String estado) {
        Semaforo semaforo = semaforoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Semáforo no encontrado"));
        semaforo.setEstado(estado);
        return semaforoRepository.save(semaforo);
    }
}
