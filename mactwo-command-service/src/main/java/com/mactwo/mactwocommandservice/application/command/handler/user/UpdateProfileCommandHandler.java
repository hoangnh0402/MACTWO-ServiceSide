package com.mactwo.mactwocommandservice.application.command.handler.user;

import com.mactwo.contants.TopicConstant;
import com.mactwo.event.UserProfileUpdatedEvent;
import com.mactwo.mactwocommandservice.application.command.command.user.UpdateProfileCommand;
import com.mactwo.mactwocommandservice.domain.model.User;
import com.mactwo.mactwocommandservice.domain.repository.UserRepository;
import com.mactwo.mactwocommandservice.infrastructure.mapper.UserMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateProfileCommandHandler {
    private final UserRepository userRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final UserMapper userMapper; // <-- Thêm UserMapper

    public UpdateProfileCommandHandler(UserRepository userRepository, KafkaTemplate<String, Object> kafkaTemplate, UserMapper userMapper) { // <-- Cập nhật constructor
        this.userRepository = userRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.userMapper = userMapper;
    }

    @Transactional
    public User handle(UpdateProfileCommand command) {
        User user = userRepository.findById(command.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // SỬA LỖI: Dùng mapper để cập nhật entity, giúp code sạch hơn
        userMapper.updateUserFromCommand(command, user);

        User updatedUser = userRepository.save(user);

        // Gửi sự kiện cập nhật
        var event = new UserProfileUpdatedEvent(
                updatedUser.getId(),
                updatedUser.getFullName(),
                updatedUser.getPhoneNumber(),
                updatedUser.getDateOfBirth(),
                updatedUser.getGender()
        );
        kafkaTemplate.send(TopicConstant.UserTopic.USER_PROFILE_UPDATED, event);

        return updatedUser;
    }
}
