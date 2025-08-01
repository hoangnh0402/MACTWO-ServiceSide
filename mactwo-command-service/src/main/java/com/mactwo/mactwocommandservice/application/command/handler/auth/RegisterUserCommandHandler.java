package com.mactwo.mactwocommandservice.application.command.handler.auth;

import com.mactwo.contants.TopicConstant;
import com.mactwo.event.UserRegisteredEvent;
import com.mactwo.mactwocommandservice.application.command.command.user.RegisterUserCommand;
import com.mactwo.mactwocommandservice.domain.model.ERole;
import com.mactwo.mactwocommandservice.domain.model.Role;
import com.mactwo.mactwocommandservice.domain.model.User;
import com.mactwo.mactwocommandservice.domain.repository.RoleRepository;
import com.mactwo.mactwocommandservice.domain.repository.UserRepository;
import com.mactwo.mactwocommandservice.infrastructure.mapper.UserMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class RegisterUserCommandHandler {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public RegisterUserCommandHandler(UserRepository userRepository,
                                      RoleRepository roleRepository,
                                      UserMapper userMapper,
                                      PasswordEncoder passwordEncoder,
                                      KafkaTemplate<String, Object> kafkaTemplate) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    public User handle(RegisterUserCommand command) {

        if (userRepository.findByEmail(command.getEmail()).isPresent()) {
            throw new IllegalStateException("Email already in use: " + command.getEmail());
        }

        User user = userMapper.toUser(command);

        user.setPasswordHash(passwordEncoder.encode(command.getPassword()));
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Default role ROLE_USER not found in database."));
        user.setRoles(Set.of(userRole));
        User savedUser = userRepository.save(user);
        UserRegisteredEvent event = userMapper.toUserRegisteredEvent(savedUser);

        kafkaTemplate.send(TopicConstant.UserTopic.USER_REGISTERED, event);

        return savedUser;
    }
}
