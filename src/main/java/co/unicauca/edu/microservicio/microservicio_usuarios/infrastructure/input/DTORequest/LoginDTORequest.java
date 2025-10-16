package co.unicauca.edu.microservicio.microservicio_usuarios.infrastructure.input.DTORequest;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class LoginDTORequest {
    @NotBlank(message = "El correo no puede estar vacío")
    String email;
    @NotBlank(message = "La contraseña no puede estar vacía")
    String password;
}
