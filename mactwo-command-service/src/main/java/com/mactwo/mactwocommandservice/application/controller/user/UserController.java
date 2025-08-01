package com.mactwo.mactwocommandservice.application.controller.user;

import com.mactwo.dto.request.user.UpdateProfileRequest;
import com.mactwo.dto.request.user.UserRegisterRequest;
import com.mactwo.mactwocommandservice.application.command.command.user.DeactivateUserCommand;
import com.mactwo.mactwocommandservice.application.command.command.user.DeleteUserCommand;
import com.mactwo.mactwocommandservice.application.command.command.user.RegisterUserCommand;
import com.mactwo.mactwocommandservice.application.command.command.user.UpdateInfoUserCommand;
import com.mactwo.mactwocommandservice.application.command.handler.auth.RegisterUserCommandHandler;
import com.mactwo.mactwocommandservice.application.command.handler.user.DeactivateUserCommandHandler;
import com.mactwo.mactwocommandservice.application.command.handler.user.DeleteUserCommandHandler;
import com.mactwo.mactwocommandservice.application.command.handler.user.UpdateInfoUserCommandHandler;
import com.mactwo.mactwocommandservice.application.controller.base.RestApiV1;
import com.mactwo.mactwocommandservice.application.controller.base.RestData;
import com.mactwo.mactwocommandservice.application.controller.base.VsResponseUtil;
import com.mactwo.mactwocommandservice.infrastructure.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestApiV1
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final RegisterUserCommandHandler registerUserCommandHandler;
    private final UpdateInfoUserCommandHandler updateInfoUserCommandHandler;
    private final DeactivateUserCommandHandler deactivateUserCommandHandler;
    private final DeleteUserCommandHandler deleteUserCommandHandler;
    private final UserMapper userMapper;

    @Tag(name = "api-user")
    @Operation(summary = "Cập nhật thông tin cá nhân của người dùng")
    @PutMapping("/{userId}/info")
    public ResponseEntity<RestData<?>> updateUserInfo(@PathVariable Long userId, @RequestBody UpdateProfileRequest request) {
        var command = new UpdateInfoUserCommand(userId, request);
        var userEntity = updateInfoUserCommandHandler.handle(command);
        return VsResponseUtil.ok(userMapper.toUserResponse(userEntity));
    }

    @Tag(name = "user-api-admin")
    @Operation(summary = "Khóa tài khoản người dùng (chỉ Admin)")
    @PatchMapping("/admin/{userId}/deactivate")
    public ResponseEntity<RestData<?>> deactivateUser(@PathVariable Long userId) {
        var command = new DeactivateUserCommand(userId);
        deactivateUserCommandHandler.handle(command);
        return VsResponseUtil.ok("User deactivated successfully.");
    }

    @Tag(name = "user-api-admin")
    @Operation(summary = "Xóa vĩnh viễn người dùng (chỉ Admin)")
    @DeleteMapping("/admin/{userId}")
    public ResponseEntity<RestData<?>> deleteUser(@PathVariable Long userId) {
        var command = new DeleteUserCommand(userId);
        deleteUserCommandHandler.handle(command);
        return VsResponseUtil.ok("User deleted successfully.");
    }
}
