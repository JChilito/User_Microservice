package co.unicauca.edu.microservicio.microservicio_usuarios.infrastructure.input.DTOResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PublicKeyResponse {
    private String alg;
    private String type;
    private String publicKey;
}
