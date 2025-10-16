package co.unicauca.edu.microservicio.microservicio_usuarios.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class Login {
    String email;
    String password;
}
