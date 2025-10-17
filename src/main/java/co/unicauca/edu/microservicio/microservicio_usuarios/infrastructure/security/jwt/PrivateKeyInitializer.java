package co.unicauca.edu.microservicio.microservicio_usuarios.infrastructure.security.jwt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class PrivateKeyInitializer {
    @PostConstruct
    public void init() throws IOException {
        String privateKey = System.getenv("PRIVATE_KEY");
        if (privateKey != null && !privateKey.isBlank()) {
            Files.writeString(Paths.get("/tmp/private.pem"), privateKey);
        }
    }
}
