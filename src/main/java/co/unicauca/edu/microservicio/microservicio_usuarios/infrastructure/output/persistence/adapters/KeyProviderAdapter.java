package co.unicauca.edu.microservicio.microservicio_usuarios.infrastructure.output.persistence.adapters;

import java.util.Base64;

import org.springframework.stereotype.Service;

import co.unicauca.edu.microservicio.microservicio_usuarios.aplication.output.KeyProviderPort;
import co.unicauca.edu.microservicio.microservicio_usuarios.infrastructure.security.jwt.RsaPropierties;

@Service
public class KeyProviderAdapter implements KeyProviderPort {
    private final RsaPropierties rsaPropierties;

    public KeyProviderAdapter(RsaPropierties rsaPropierties) {
        this.rsaPropierties = rsaPropierties;
    }

    @Override
    public String getPublicKeyAsString() {
        var publicKey = rsaPropierties.publicKey();
        // Convertimos la clave a formato PEM
        String encoded = Base64.getEncoder().encodeToString(publicKey.getEncoded());

        return "-----BEGIN PUBLIC KEY-----\n" +
                encoded +
                "\n-----END PUBLIC KEY-----";
    }
}
