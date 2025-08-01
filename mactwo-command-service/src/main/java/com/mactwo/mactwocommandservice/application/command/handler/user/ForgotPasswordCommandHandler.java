package com.mactwo.mactwocommandservice.application.command.handler.user;

import com.mactwo.contants.TopicConstant;
import com.mactwo.event.PasswordResetOTPSentEvent;
import com.mactwo.mactwocommandservice.application.command.command.user.ForgotPasswordCommand;
import com.mactwo.mactwocommandservice.domain.model.User;
import com.mactwo.mactwocommandservice.domain.repository.UserRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class ForgotPasswordCommandHandler {
    private final UserRepository userRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ForgotPasswordCommandHandler(UserRepository userRepository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.userRepository = userRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    public void handle(ForgotPasswordCommand command) {
        User user = userRepository.findByEmail(command.getEmail())
                .orElseThrow(() -> new RuntimeException("User with this email not found"));

        // Tạo mã OTP ngẫu nhiên (ví dụ: 6 chữ số)
        String otp = String.format("%06d", new Random().nextInt(999999));

        // Lưu OTP và thời gian hết hạn (ví dụ: 5 phút)
        user.setOtpCode(otp);
        user.setOtpExpiryDate(LocalDateTime.now().plusMinutes(5));
        userRepository.save(user);

        // Gửi sự kiện để hệ thống khác (ví dụ: email service) có thể gửi OTP cho người dùng
        var event = new PasswordResetOTPSentEvent(user.getId(), user.getEmail(), otp);
        // Có thể tạo topic riêng cho OTP
        kafkaTemplate.send(TopicConstant.UserTopic.USER_REGISTERED, event);
    }
}
