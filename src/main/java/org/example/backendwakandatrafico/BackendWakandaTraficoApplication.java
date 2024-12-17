package org.example.backendwakandatrafico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BackendWakandaTraficoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendWakandaTraficoApplication.class, args);
    }

}
