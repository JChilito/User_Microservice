package co.unicauca.edu.microservicio.microservicio_usuarios.aplication.output;

public interface PasswordEncoderIntPort {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);
}
