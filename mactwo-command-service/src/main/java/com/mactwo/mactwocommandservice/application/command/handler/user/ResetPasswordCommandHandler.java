package com.mactwo.mactwocommandservice.application.command.handler.user;

import com.mactwo.mactwocommandservice.domain.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.mactwo.mactwocommandservice.domain.repository.UserRepository;
import com.mactwo.mactwocommandservice.application.command.command.user.ResetPasswordCommand;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ResetPasswordCommandHandler {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResetPasswordCommandHandler(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void handle(ResetPasswordCommand command) {
        User user = userRepository.findByEmail(command.getEmail())
                .orElseThrow(() -> new RuntimeException("User with this email not found"));

        if (user.getOtpCode() == null || !user.getOtpCode().equals(command.getOtp())) {
            throw new RuntimeException("Invalid OTP");
        }

        if (user.getOtpExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP has expired");
        }

        user.setPasswordHash(passwordEncoder.encode(command.getNewPassword()));

        user.setOtpCode(null);
        user.setOtpExpiryDate(null);
        userRepository.save(user);
    }
}
