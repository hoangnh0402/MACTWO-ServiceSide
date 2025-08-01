package com.mactwo.mactwocommandservice.application.command.handler.auth;

import com.mactwo.mactwocommandservice.application.command.command.auth.LogoutCommand;
import com.mactwo.mactwocommandservice.infrastructure.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class LogoutCommandHandler {

    private final RedisTemplate<String, Object> redisTemplate;
    private final JwtService jwtService;

    public void handle(LogoutCommand command) {
        String jwt = command.getJwtToken();
        Date expirationDate = jwtService.extractExpiration(jwt);
        long remainingTimeInMillis = expirationDate.getTime() - System.currentTimeMillis();

        if (remainingTimeInMillis > 0) {
            redisTemplate.opsForValue().set(
                    "blacklist:" + jwt,
                    true,
                    Duration.ofMillis(remainingTimeInMillis)
            );
        }
    }
}
