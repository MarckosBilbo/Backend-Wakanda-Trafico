package org.example.backendwakandatrafico.domain;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Semaforo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ubicacion;
    private String estado; // ROJO, NARANJA, VERDE
    private int cochesEsperando; // Número de coches detrás del semáforo
}
