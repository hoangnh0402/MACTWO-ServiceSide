package com.mactwo.mactwocommandservice.application.command.handler.user;

import com.mactwo.contants.TopicConstant;
import com.mactwo.event.UserProfileUpdatedEvent;
import com.mactwo.mactwocommandservice.application.command.command.user.UpdateInfoUserCommand;
import com.mactwo.mactwocommandservice.domain.model.User;
import com.mactwo.mactwocommandservice.domain.repository.UserRepository;
import com.mactwo.mactwocommandservice.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateInfoUserCommandHandler {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public User handle(UpdateInfoUserCommand command) {
        User user = userRepository.findById(command.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateUserFromCommand(command, user);
        User updatedUser = userRepository.save(user);

        var event = new UserProfileUpdatedEvent(
                updatedUser.getId(), updatedUser.getFullName(), updatedUser.getPhoneNumber(),
                updatedUser.getDateOfBirth(), updatedUser.getGender()
        );
        kafkaTemplate.send(TopicConstant.UserTopic.USER_PROFILE_UPDATED, event);

        return updatedUser;
    }
}
