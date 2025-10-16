package co.unicauca.edu.microservicio.microservicio_usuarios.domain.usecases;

import java.util.List;
import java.util.Optional;

import co.unicauca.edu.microservicio.microservicio_usuarios.aplication.input.UserManagementIntPort;
import co.unicauca.edu.microservicio.microservicio_usuarios.aplication.output.UserManagementPersistenceIntPort;
import co.unicauca.edu.microservicio.microservicio_usuarios.domain.models.User;

public class UserManagementService implements UserManagementIntPort {

    private final UserManagementPersistenceIntPort userManagementIntPort;

    public UserManagementService(UserManagementPersistenceIntPort userManagementIntPort) {
        this.userManagementIntPort = userManagementIntPort;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userManagementIntPort.getAllUsers();
        return users;
    }

    @Override
    public User getUserById(String id) {
        Optional<User> userOpt = userManagementIntPort.getUserById(id);
        if (userOpt.isPresent()) {
            return userOpt.get();
        } else {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + id);
        }
    }

    @Override
    public User updateUser(String id, User user) {
        Optional<User> existingUserOpt = userManagementIntPort.getUserById(id);
        if (!existingUserOpt.isPresent()) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + id);
        }
        User existingUser = existingUserOpt.get();
        existingUser.setId(id);
        existingUser.setUsername(user.getUsername());
        existingUser.setName(user.getName());
        existingUser.setLastName(user.getLastName());
        existingUser.setRole(user.getRole());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        return userManagementIntPort.saveUser(existingUser);
    }

    @Override
    public void deleteUser(String id) {
        if (!userManagementIntPort.getUserById(id).isPresent()) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + id);
        }
        userManagementIntPort.deleteUser(id);
    }
    
}
