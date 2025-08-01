package com.mactwo.mactwocommandservice.application.controller;

import com.mactwo.mactwocommandservice.application.controller.base.RestApiV1;
import com.mactwo.mactwocommandservice.application.controller.base.RestData;
import com.mactwo.mactwocommandservice.application.controller.base.VsResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RestApiV1
@RequestMapping("/debug")
@RequiredArgsConstructor
public class DebugController {

    private final PasswordEncoder passwordEncoder;

    /**
     * API để mã hóa một mật khẩu thô.
     * @param payload JSON chứa mật khẩu, ví dụ: { "password": "password123" }
     * @return Chuỗi mật khẩu đã được mã hóa bằng BCrypt.
     */
    @PostMapping("/hash-password")
    public ResponseEntity<RestData<?>> hashPassword(@RequestBody Map<String, String> payload) {
        String rawPassword = payload.get("password");
        if (rawPassword == null) {
            return VsResponseUtil.error(org.springframework.http.HttpStatus.BAD_REQUEST, "Payload must contain 'password' field.");
        }
        String hashedPassword = passwordEncoder.encode(rawPassword);
        return VsResponseUtil.ok(Map.of("rawPassword", rawPassword, "hashedPassword", hashedPassword));
    }
}
