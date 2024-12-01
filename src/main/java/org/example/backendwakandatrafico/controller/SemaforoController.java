package org.example.backendwakandatrafico.controller;


import org.example.backendwakandatrafico.domain.Semaforo;
import org.example.backendwakandatrafico.service.SemaforoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/semaforos")
public class SemaforoController {
    private final SemaforoService semaforoService;

    public SemaforoController(SemaforoService semaforoService) {
        this.semaforoService = semaforoService;
    }

    @GetMapping
    public List<Semaforo> listarSemaforos() {
        return semaforoService.listarSemaforos();
    }

    @PutMapping("/{id}/estado")
    public Semaforo actualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        return semaforoService.actualizarEstadoSemaforo(id, estado);
    }
}
