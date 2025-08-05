package com.mactwo.mactwocommandservice.application.controller.auth;

import com.mactwo.dto.request.auth.LoginRequest;
import com.mactwo.dto.request.user.UserRegisterRequest;
import com.mactwo.mactwocommandservice.application.command.command.auth.LoginCommand;
import com.mactwo.mactwocommandservice.application.command.command.auth.LogoutCommand;
import com.mactwo.mactwocommandservice.application.command.command.user.RegisterUserCommand;
import com.mactwo.mactwocommandservice.application.command.handler.auth.LoginCommandHandler;
import com.mactwo.mactwocommandservice.application.command.handler.auth.LogoutCommandHandler;
import com.mactwo.mactwocommandservice.application.command.handler.auth.RegisterUserCommandHandler;
import com.mactwo.mactwocommandservice.application.controller.base.RestApiV1;
import com.mactwo.mactwocommandservice.application.controller.base.RestData;
import com.mactwo.mactwocommandservice.application.controller.base.VsResponseUtil;
import com.mactwo.mactwocommandservice.infrastructure.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestApiV1
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginCommandHandler loginCommandHandler;
    private final LogoutCommandHandler logoutCommandHandler;
    private final RegisterUserCommandHandler registerUserCommandHandler;
    private final UserMapper userMapper;

    @Tag(name = "api-auth")
    @Operation(summary = "Đăng nhập")
    @PostMapping("/login")
    public ResponseEntity<RestData<?>> login(@RequestBody LoginRequest request) {
        var command = new LoginCommand(request);
        var response = loginCommandHandler.handle(command);
        return VsResponseUtil.ok(response);
    }

    @Tag(name = "api-auth")
    @Operation(summary = "Đăng xuất")
    @PostMapping("/logout")
    public ResponseEntity<RestData<?>> logout(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            var command = new LogoutCommand(jwt);
            logoutCommandHandler.handle(command);
        }

        return VsResponseUtil.ok("Logout successful.");
    }

    @Tag(name = "api-auth")
    @Operation(summary = "Đăng ký tài khoản")
    @PostMapping("/register")
    public ResponseEntity<RestData<?>> registerUser(@RequestBody UserRegisterRequest request) {

        var command = new RegisterUserCommand(request);

        var registeredUserEntity = registerUserCommandHandler.handle(command);

        var userResponse = userMapper.toUserResponse(registeredUserEntity);

        return VsResponseUtil.ok(HttpStatus.CREATED, userResponse);
    }
}