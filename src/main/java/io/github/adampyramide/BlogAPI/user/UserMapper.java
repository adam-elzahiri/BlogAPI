package io.github.adam_elzahiri.BlogAPI.user;

import io.github.adam_elzahiri.BlogAPI.auth.AuthRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User authDTOToEntity(AuthRequest dto);

    PublicUserResponse toPublicDTO(User user);

    @Mapping(target = "id", ignore = true)
    void updateEntity(UpdateUserRequest request, @MappingTarget User entity);

}
