package co.unicauca.edu.microservicio.microservicio_usuarios.infrastructure.output.persistence.adapters;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import co.unicauca.edu.microservicio.microservicio_usuarios.aplication.output.PasswordEncoderIntPort;

@Component
public class PasswordEncoderImplAdapter implements PasswordEncoderIntPort{
    private final PasswordEncoder passwordEncoder;

    public PasswordEncoderImplAdapter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }


    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
