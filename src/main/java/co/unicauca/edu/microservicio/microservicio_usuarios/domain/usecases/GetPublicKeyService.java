package co.unicauca.edu.microservicio.microservicio_usuarios.domain.usecases;

import co.unicauca.edu.microservicio.microservicio_usuarios.aplication.input.GetPublicKeyIntPort;
import co.unicauca.edu.microservicio.microservicio_usuarios.aplication.output.KeyProviderPort;

public class GetPublicKeyService implements GetPublicKeyIntPort {

    private final KeyProviderPort keyProviderPort;

    public GetPublicKeyService(KeyProviderPort keyProviderPort) {
        this.keyProviderPort = keyProviderPort;
    }

    @Override
    public String getPublicKey() {
        return keyProviderPort.getPublicKeyAsString();
    }
}
