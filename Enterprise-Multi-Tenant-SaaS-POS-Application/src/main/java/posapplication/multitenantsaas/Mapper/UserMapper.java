package posapplication.multitenantsaas.Mapper;

import org.mapstruct.Mapper;
import posapplication.multitenantsaas.Dtos.UserDtos;
import posapplication.multitenantsaas.ModelClass.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDtos toDto(User user);
}
