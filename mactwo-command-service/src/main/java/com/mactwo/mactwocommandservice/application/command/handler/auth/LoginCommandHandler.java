package com.mactwo.mactwocommandservice.application.command.handler.auth;

import com.mactwo.dto.response.LoginResponse;
import com.mactwo.mactwocommandservice.application.command.command.auth.LoginCommand;
import com.mactwo.mactwocommandservice.infrastructure.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginCommandHandler {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    public LoginResponse handle(LoginCommand command) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        command.getEmail(),
                        command.getPassword()
                )
        );

        var userDetails = userDetailsService.loadUserByUsername(command.getEmail());
        var jwtToken = jwtService.generateToken(userDetails);

        return LoginResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}
