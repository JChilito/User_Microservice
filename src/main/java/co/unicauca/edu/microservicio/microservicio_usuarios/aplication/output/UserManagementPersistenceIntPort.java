package co.unicauca.edu.microservicio.microservicio_usuarios.aplication.output;

import java.util.List;
import java.util.Optional;

import co.unicauca.edu.microservicio.microservicio_usuarios.domain.models.User;

public interface UserManagementPersistenceIntPort {
    public boolean userExistsByUsername(String username);
    public boolean userExistsByEmail(String email);
    public Optional<User> findByEmail(String email);
    public User saveUser(User user);
    public List<User> getAllUsers();
    public Optional<User> getUserById(String id);
    public void deleteUser(String id);
}
