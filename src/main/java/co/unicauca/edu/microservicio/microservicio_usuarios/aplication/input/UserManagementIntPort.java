package co.unicauca.edu.microservicio.microservicio_usuarios.aplication.input;

import java.util.List;

import co.unicauca.edu.microservicio.microservicio_usuarios.domain.models.User;

public interface UserManagementIntPort {
    public List<User> getAllUsers();
    public User getUserById(String id);
    public User updateUser(String id, User user);
    public void deleteUser(String id);
}
