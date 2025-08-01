package com.mactwo.mactwocommandservice.infrastructure.mapper;

import com.mactwo.dto.response.UserResponse;
import com.mactwo.event.UserRegisteredEvent;
import com.mactwo.mactwocommandservice.application.command.command.user.RegisterUserCommand;
import com.mactwo.mactwocommandservice.application.command.command.user.UpdateInfoUserCommand;
import com.mactwo.mactwocommandservice.domain.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "active", ignore = true)
    User toUser(RegisterUserCommand request);

    @Mapping(source = "id", target = "userId")
    UserRegisteredEvent toUserRegisteredEvent(User user);

    UserResponse toUserResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateUserFromCommand(UpdateInfoUserCommand command, @MappingTarget User user);
}
