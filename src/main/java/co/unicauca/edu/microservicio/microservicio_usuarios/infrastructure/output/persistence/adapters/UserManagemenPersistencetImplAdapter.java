package co.unicauca.edu.microservicio.microservicio_usuarios.infrastructure.output.persistence.adapters;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.unicauca.edu.microservicio.microservicio_usuarios.aplication.output.UserManagementPersistenceIntPort;
import co.unicauca.edu.microservicio.microservicio_usuarios.domain.models.User;
import co.unicauca.edu.microservicio.microservicio_usuarios.infrastructure.output.persistence.documents.UserDocument;
import co.unicauca.edu.microservicio.microservicio_usuarios.infrastructure.output.persistence.repositories.UserRepository;

@Service
public class UserManagemenPersistencetImplAdapter implements UserManagementPersistenceIntPort {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserManagemenPersistencetImplAdapter(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public boolean userExistsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    @Transactional
    public Optional<User> findByEmail(String email) {
        Optional<UserDocument> userDocument = userRepository.findByEmail(email);
        Optional<User> user = modelMapper.map(userDocument, new TypeToken<Optional<User>>() {}.getType());
        return user;
    }

    @Override
    public User saveUser(User user) {
        UserDocument userDocument = this.modelMapper.map(user, UserDocument.class);
        UserDocument savedUserDocument = userRepository.save(userDocument);
        User savedUser = this.modelMapper.map(savedUserDocument, User.class);
        return savedUser;
    }

    @Override
    public List<User> getAllUsers() {
        Iterable<UserDocument> userDocuments = userRepository.findAll();
        List<User> users = modelMapper.map(userDocuments, new TypeToken<List<User>>() {}.getType());
        return users;
    }

    @Override
    public Optional<User> getUserById(String id) {
        Optional<UserDocument> userDocument = userRepository.findById(id);
        Optional<User> user = modelMapper.map(userDocument, new TypeToken<Optional<User>>() {}.getType());
        return user;
    }

    @Override
    public void deleteUser(String id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public boolean userExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    
}
