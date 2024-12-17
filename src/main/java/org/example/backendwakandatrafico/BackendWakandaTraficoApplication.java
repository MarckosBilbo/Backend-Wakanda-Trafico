package org.example.backendwakandatrafico;

import org.example.backendwakandatrafico.domain.Calle;
import org.example.backendwakandatrafico.domain.Semaforo;
import org.example.backendwakandatrafico.repos.CalleRepository;
import org.example.backendwakandatrafico.repos.SemaforoRepository;
import org.example.backendwakandatrafico.service.CalleService;
import org.example.backendwakandatrafico.service.SemaforoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableDiscoveryClient
public class BackendWakandaTraficoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendWakandaTraficoApplication.class, args);
    }

    @Bean
    CommandLineRunner run(CalleRepository calleRepository, SemaforoRepository semaforoRepository, CalleService calleService, SemaforoService semaforoService) {
        return args -> {
            Random random = new Random();

            // Crear calles iniciales
            Calle calle1 = new Calle();
            calle1.setNombre("Avenida T'Challa");
            calle1.setParkingsDisponibles(20);
            calle1.setCochesEnCirculacion(50);

            Calle calle2 = new Calle();
            calle2.setNombre("Calle Vibranium");
            calle2.setParkingsDisponibles(15);
            calle2.setCochesEnCirculacion(30);

            Calle calle3 = new Calle();
            calle3.setNombre("Camino del Monte Bashenga");
            calle3.setParkingsDisponibles(10);
            calle3.setCochesEnCirculacion(40);

            // Guardar las calles en la base de datos
            List<Calle> calles = calleRepository.saveAll(List.of(calle1, calle2, calle3));
            calles.forEach(calle -> System.out.println("Calle creada: " + calle.getNombre() + " - Parkings disponibles: " + calle.getParkingsDisponibles()));

            // Crear semáforos iniciales
            Semaforo semaforo1 = new Semaforo();
            semaforo1.setUbicacion("Avenida T'Challa - Intersección 1");
            semaforo1.setEstado("VERDE");
            semaforo1.setCochesEsperando(3);

            Semaforo semaforo2 = new Semaforo();
            semaforo2.setUbicacion("Calle Vibranium - Intersección 2");
            semaforo2.setEstado("ROJO");
            semaforo2.setCochesEsperando(10);

            Semaforo semaforo3 = new Semaforo();
            semaforo3.setUbicacion("Camino del Monte Bashenga - Intersección 3");
            semaforo3.setEstado("NARANJA");
            semaforo3.setCochesEsperando(5);

            // Guardar los semáforos en la base de datos
            List<Semaforo> semaforos = semaforoRepository.saveAll(List.of(semaforo1, semaforo2, semaforo3));
            semaforos.forEach(semaforo -> System.out.println("Semáforo creado: " + semaforo.getUbicacion() + " - Estado: " + semaforo.getEstado()));

            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(() -> {
                System.out.println("\n[INFO] Estado actual de las calles y semáforos");

                System.out.println("[INFO] Listando todas las calles...");
                List<Calle> todasLasCalles = calleService.listarCalles();
                todasLasCalles.forEach(calle -> {
                    int nuevosParkings = Math.max(0, calle.getParkingsDisponibles() + random.nextInt(3) - 1);
                    int nuevosCoches = Math.max(0, calle.getCochesEnCirculacion() + random.nextInt(5) - 2);
                    calle.setParkingsDisponibles(nuevosParkings);
                    calle.setCochesEnCirculacion(nuevosCoches);
                    calleRepository.save(calle);
                    System.out.println("Calle: " + calle.getNombre() + " - Parkings disponibles: " + calle.getParkingsDisponibles() + " - Coches en circulación: " + calle.getCochesEnCirculacion());
                });

                System.out.println("\n[INFO] Listando todos los semáforos...");
                List<Semaforo> todosLosSemaforos = semaforoService.listarSemaforos();
                todosLosSemaforos.forEach(semaforo -> {
                    String[] estados = {"ROJO", "NARANJA", "VERDE"};
                    String nuevoEstado = estados[random.nextInt(estados.length)];
                    int nuevosCochesEsperando = Math.max(0, semaforo.getCochesEsperando() + random.nextInt(4) - 1);
                    semaforo.setEstado(nuevoEstado);
                    semaforo.setCochesEsperando(nuevosCochesEsperando);
                    semaforoRepository.save(semaforo);
                    System.out.println("Semáforo en: " + semaforo.getUbicacion() + " - Estado: " + semaforo.getEstado() + " - Coches esperando: " + semaforo.getCochesEsperando());
                });
            }, 0, 30, TimeUnit.SECONDS);
        };
    }
}
