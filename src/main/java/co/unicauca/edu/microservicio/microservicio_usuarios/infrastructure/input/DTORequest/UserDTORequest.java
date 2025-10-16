package co.unicauca.edu.microservicio.microservicio_usuarios.infrastructure.input.DTORequest;

import co.unicauca.edu.microservicio.microservicio_usuarios.domain.models.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class UserDTORequest {
    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    private String username;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
    private String lastName;
    @NotBlank(message = "La edad no puede estar vacía")
    @Min(value = 0, message = "La edad debe ser un número positivo")
    private int age;
    @Email(message = "El correo debe tener un formato válido")
    private String email;
    @NotNull(message = "El rol no puede estar vacío")
    private Role role;
    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;
}
