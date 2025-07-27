package io.github.adam_elzahiri.BlogAPI.user;

import io.github.adam_elzahiri.BlogAPI.auth.AuthRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User authDTOToEntity(AuthRequest dto);

    PublicUserResponse toPublicDTO(User user);

}
