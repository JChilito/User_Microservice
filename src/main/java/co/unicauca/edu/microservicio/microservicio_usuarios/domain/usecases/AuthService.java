package co.unicauca.edu.microservicio.microservicio_usuarios.domain.usecases;

import java.util.Optional;

import co.unicauca.edu.microservicio.microservicio_usuarios.aplication.input.AuthIntPort;
import co.unicauca.edu.microservicio.microservicio_usuarios.aplication.output.PasswordEncoderIntPort;
import co.unicauca.edu.microservicio.microservicio_usuarios.aplication.output.TokenIntPort;
import co.unicauca.edu.microservicio.microservicio_usuarios.aplication.output.UserManagementPersistenceIntPort;
import co.unicauca.edu.microservicio.microservicio_usuarios.domain.models.AuthTokens;
import co.unicauca.edu.microservicio.microservicio_usuarios.domain.models.AuthUser;
import co.unicauca.edu.microservicio.microservicio_usuarios.domain.models.Login;
import co.unicauca.edu.microservicio.microservicio_usuarios.domain.models.RefreshToken;
import co.unicauca.edu.microservicio.microservicio_usuarios.domain.models.User;

public class AuthService implements AuthIntPort {
    private final UserManagementPersistenceIntPort userManagementPersistenceIntPort;
    private final PasswordEncoderIntPort passwordEncoderIntPort;
    private final TokenIntPort tokenIntPort;

    public AuthService(UserManagementPersistenceIntPort userManagementPersistenceIntPort, PasswordEncoderIntPort passwordEncoderIntPort, TokenIntPort tokenIntPort) {
        this.userManagementPersistenceIntPort = userManagementPersistenceIntPort;
        this.passwordEncoderIntPort = passwordEncoderIntPort;
        this.tokenIntPort = tokenIntPort;
    }

    @Override
    public AuthTokens login(Login login) {
        if (userManagementPersistenceIntPort.userExistsByEmail(login.getEmail())) {
            Optional<User> userOptional = userManagementPersistenceIntPort.findByEmail(login.getEmail());
            User user = userOptional.get();
            if(!passwordEncoderIntPort.matches(login.getPassword(),user.getPassword())){
                throw new IllegalArgumentException("Credenciales inválidas.");
            }
            String token = tokenIntPort.generateToken(new AuthUser(user.getId(),user.getRole()));
            RefreshToken refreshToken = tokenIntPort.generateRefreshToken(user.getId());
            AuthTokens authTokens = new AuthTokens(token, refreshToken.getToken());
            return authTokens;
        }else{
            throw new IllegalArgumentException("El usuario no existe.");
        }
    }

    @Override
    public User createUser(User user) {
        if (userManagementPersistenceIntPort.userExistsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("El usuario ya existe.");
        }else if (userManagementPersistenceIntPort.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El correo ya está en uso.");
        }
        //TODO: Validar que la restricción del correo no esta funcionando, crea el correo repetido

        String encodedPassword = passwordEncoderIntPort.encode(user.getPassword());
        user.setPassword(encodedPassword);

        System.out.println("Estado del usuario en el Servicio del Auth:");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Rol: " + user.getRole());
        System.out.println("Contraseña: " + user.getLastName());
        System.out.println("Estado: " + user.getLastName());

        return userManagementPersistenceIntPort.saveUser(user);
    }

    @Override
    public AuthTokens refreshToken(String refreshToken) {
        Optional<RefreshToken> refreshTokenOptional = tokenIntPort.findByToken(refreshToken);
        if(refreshTokenOptional.isEmpty()){
            throw new IllegalArgumentException("Token de refresco inválido.");
        }
        RefreshToken token = refreshTokenOptional.get();
        if(token.isExpired()){
            throw new IllegalArgumentException("Token de refresco expirado.");
        }
        System.out.println("Si encontro el token de refresco");
        User user = userManagementPersistenceIntPort.getUserById(token.getUserId()).get();
        String newAccesToken = tokenIntPort.generateToken(new AuthUser(token.getUserId(), user.getRole()));
        AuthTokens authTokens = new AuthTokens(newAccesToken, refreshToken);
        return authTokens;
    }
    
}
