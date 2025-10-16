package co.unicauca.edu.microservicio.microservicio_usuarios.infrastructure.input.DTORequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class RefreshTokenRequest {
    private String refreshToken;
}
