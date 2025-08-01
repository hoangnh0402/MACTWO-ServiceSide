package com.mactwo.mactwocommandservice.application.command.handler.user;

import com.mactwo.contants.TopicConstant;
import com.mactwo.event.UserDeletedEvent;
import com.mactwo.mactwocommandservice.application.command.command.user.DeleteUserCommand;
import com.mactwo.mactwocommandservice.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteUserCommandHandler {
    private final UserRepository userRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public void handle(DeleteUserCommand command) {
        if (!userRepository.findById(command.getUserId()).isPresent()) {
            throw new RuntimeException("User not found");
        }

        userRepository.deleteById(command.getUserId());

        var event = new UserDeletedEvent(command.getUserId());
        kafkaTemplate.send(TopicConstant.UserTopic.USER_REGISTERED, event);
    }
}
