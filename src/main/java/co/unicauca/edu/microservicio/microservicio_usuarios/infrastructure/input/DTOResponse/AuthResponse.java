package co.unicauca.edu.microservicio.microservicio_usuarios.infrastructure.input.DTOResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
}
