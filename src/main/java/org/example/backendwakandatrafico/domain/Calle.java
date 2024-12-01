package org.example.backendwakandatrafico.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Calle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int parkingsDisponibles; // Número de parkings libres
    private int cochesEnCirculacion; // Número de coches en la calle
}
