
package com.integrationtrust.engine.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {

        return Map.of(
            "application", "Integration Trust Engine",
            "version", "1.0.0",
            "status", "OPERATIONAL",
            "message", "Backend iniciado com sucesso!"
        );
    }

    @GetMapping("/api/health")
    public Map<String, String> health() {

        return Map.of(
            "status", "UP",
            "service", "Integration Trust Engine"
        );
    }
}
