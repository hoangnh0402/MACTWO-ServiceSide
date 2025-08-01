package com.mactwo.mactwocommandservice.application.command.handler.user;

import com.mactwo.mactwocommandservice.application.command.command.user.ChangePasswordCommand;
import com.mactwo.mactwocommandservice.domain.model.User;
import com.mactwo.mactwocommandservice.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChangePasswordCommandHandler {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // <-- Tiêm PasswordEncoder

    public ChangePasswordCommandHandler(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void handle(ChangePasswordCommand command) {
        User user = userRepository.findById(command.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(command.getOldPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Incorrect old password");
        }

        user.setPasswordHash(passwordEncoder.encode(command.getNewPassword()));
        userRepository.save(user);
    }
}
