package co.unicauca.edu.microservicio.microservicio_usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import co.unicauca.edu.microservicio.microservicio_usuarios.infrastructure.security.jwt.RsaPropierties;

@SpringBootApplication
@EnableConfigurationProperties(RsaPropierties.class)
public class MicroservicioUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioUsuariosApplication.class, args);
	}
}
