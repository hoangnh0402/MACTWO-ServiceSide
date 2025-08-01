package com.mactwo.mactwocommandservice.application.command.handler.user;

import com.mactwo.contants.TopicConstant;
import com.mactwo.event.UserStatusChangedEvent;
import com.mactwo.mactwocommandservice.application.command.command.user.DeactivateUserCommand;
import com.mactwo.mactwocommandservice.domain.model.User;
import com.mactwo.mactwocommandservice.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeactivateUserCommandHandler {
    private final UserRepository userRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public void handle(DeactivateUserCommand command) {
        User user = userRepository.findById(command.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setActive(false);
        userRepository.save(user);

        var event = new UserStatusChangedEvent(user.getId(), user.isActive());
        kafkaTemplate.send(TopicConstant.UserTopic.USER_REGISTERED, event);
    }
}
