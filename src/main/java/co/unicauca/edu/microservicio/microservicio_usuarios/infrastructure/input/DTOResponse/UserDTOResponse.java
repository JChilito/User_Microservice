package co.unicauca.edu.microservicio.microservicio_usuarios.infrastructure.input.DTOResponse;

import co.unicauca.edu.microservicio.microservicio_usuarios.domain.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class UserDTOResponse {
    private String id;
    private String username;
    private String name;
    private String lastName;
    private int age;
    private Role role;
    private String email;

    public UserDTOResponse(){   
    }
}
