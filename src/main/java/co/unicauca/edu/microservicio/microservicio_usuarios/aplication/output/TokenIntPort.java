package co.unicauca.edu.microservicio.microservicio_usuarios.aplication.output;

import java.util.Optional;

import co.unicauca.edu.microservicio.microservicio_usuarios.domain.models.AuthUser;
import co.unicauca.edu.microservicio.microservicio_usuarios.domain.models.RefreshToken;

public interface TokenIntPort {
    String generateToken(AuthUser authUser);
    RefreshToken generateRefreshToken(String userId);
    boolean validateToken(String token);
    String getUserIdFromToken(String token);
    Optional<RefreshToken> findByToken(String token);
}
