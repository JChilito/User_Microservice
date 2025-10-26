package co.unicauca.edu.microservicio.microservicio_usuarios.infrastructure.input.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.unicauca.edu.microservicio.microservicio_usuarios.aplication.input.UserManagementIntPort;
import co.unicauca.edu.microservicio.microservicio_usuarios.domain.models.User;
import co.unicauca.edu.microservicio.microservicio_usuarios.infrastructure.input.DTORequest.UserDTORequest;
import co.unicauca.edu.microservicio.microservicio_usuarios.infrastructure.input.DTOResponse.UserDTOResponse;
import co.unicauca.edu.microservicio.microservicio_usuarios.infrastructure.input.mappers.UserMapperInfrastructureDomain;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserManagementIntPort userManagementUseCaseIntPort;
    private final UserMapperInfrastructureDomain objMapper;

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    //@PreAuthorize("hasAnyRole('ADMINISTRATOR','CLIENT')")
    public ResponseEntity<List<UserDTOResponse>> getAllUsers() {
        List<User> users = userManagementUseCaseIntPort.getAllUsers();
        List<UserDTOResponse> userDTOs = objMapper.toDTOList(users);
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CLIENT','TOURIST_GUIDE')")
    public ResponseEntity<UserDTOResponse> getUserById(@PathVariable String id) {
        User user = userManagementUseCaseIntPort.getUserById(id);
        UserDTOResponse userDTO = objMapper.toDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR','CLIENT','TOURIST_GUIDE')")
    public ResponseEntity<UserDTOResponse> updateUser(@PathVariable String id, @RequestBody UserDTORequest userDTORequest) {
        User user = objMapper.toDomain(userDTORequest);
        User updatedUser = userManagementUseCaseIntPort.updateUser(id, user);
        UserDTOResponse userDTOResponse = objMapper.toDTO(updatedUser);
        return ResponseEntity.ok(userDTOResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userManagementUseCaseIntPort.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
}
