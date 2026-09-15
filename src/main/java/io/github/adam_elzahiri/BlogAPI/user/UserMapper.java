package io.github.adam_elzahiri.BlogAPI.user;

import io.github.adam_elzahiri.BlogAPI.auth.AuthRequest;
import io.github.adam_elzahiri.BlogAPI.filestorage.CloudinaryFileStorageService;
import io.github.adam_elzahiri.BlogAPI.filestorage.FileValidationRule;
import io.github.adam_elzahiri.BlogAPI.filestorage.MimeTypeRules;
import io.github.adam_elzahiri.BlogAPI.user.dto.UserPreviewResponse;
import io.github.adam_elzahiri.BlogAPI.user.dto.UpdateUserRequest;
import io.github.adam_elzahiri.BlogAPI.user.dto.UserProfileResponse;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class UserMapper {

    // DEPENDENCIES
    @Autowired
    private CloudinaryFileStorageService fileStorageService;

    // VARIABLES
    private static final FileValidationRule fileValidationRule = MimeTypeRules.IMAGE_ONLY;

    // CONVERTERS
    @Mapping(target = "id", ignore = true)
    public abstract User toEntity(AuthRequest dto);

    public abstract UserPreviewResponse toUserPreviewResponse(User user);

    public abstract UserProfileResponse toUserProfileResponse(User user);

    // UPDATERS
    @Mapping(target = "id", ignore = true)
    public abstract void updateEntity(UpdateUserRequest request, @MappingTarget User entity);

    // ENRICHERS
    @AfterMapping
    protected void enrichUserPreviewResponse(User user, @MappingTarget UserPreviewResponse response) {
        response.setAvatarUrl(getAvatarUrl(user));
    }

    @AfterMapping
    protected void enrichUserProfileResponse(User user, @MappingTarget UserProfileResponse response) {
        response.setAvatarUrl(getAvatarUrl(user));
    }

    private String getAvatarUrl(User user) {
        return fileStorageService.getUrl(user.getAvatarId(), fileValidationRule);
    }

}
