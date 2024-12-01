package org.example.backendwakandatrafico.controller;


import org.example.backendwakandatrafico.domain.Calle;
import org.example.backendwakandatrafico.service.CalleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calles")
public class CalleController {
    private final CalleService calleService;

    public CalleController(CalleService calleService) {
        this.calleService = calleService;
    }

    @GetMapping
    public List<Calle> listarCalles() {
        return calleService.listarCalles();
    }

    @GetMapping("/buscar-parking")
    public Calle buscarParking() {
        return calleService.buscarCalleConMasParkings();
    }

    @PutMapping("/{id}/parkings")
    public Calle actualizarParkings(@PathVariable Long id, @RequestParam int parkingsDisponibles) {
        return calleService.actualizarParkings(id, parkingsDisponibles);
    }
}

