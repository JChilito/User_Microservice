package co.unicauca.edu.microservicio.microservicio_usuarios.infrastructure.output.persistence.mappers;

import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.Converter;

@Configuration
public class UserMapper {

    @Bean
    public ModelMapper createUserMapper(){
        ModelMapper mapper = new ModelMapper();

        // Conversión de ObjectId -> String
        Converter<ObjectId, String> objectIdToString = ctx ->
                ctx.getSource() == null ? null : ctx.getSource().toHexString();

        // Conversión de String -> ObjectId (opcional)
        Converter<String, ObjectId> stringToObjectId = ctx ->
                (ctx.getSource() == null || ctx.getSource().isEmpty()) ? null : new ObjectId(ctx.getSource());

        // Registrar ambos convertidores
        mapper.addConverter(objectIdToString);
        mapper.addConverter(stringToObjectId);
        return mapper;
    }
    
}
