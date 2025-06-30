package io.github.adam_elzahiri.BlogAPI.user;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User authDTOToEntity(AuthUserDTO dto);

    PublicUserDTO toPublicDTO(User user);

}
