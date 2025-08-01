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

@Service
public class RegisterUserCommandHandler {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public RegisterUserCommandHandler(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, KafkaTemplate<String, Object> kafkaTemplate, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.kafkaTemplate = kafkaTemplate;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public User handle(RegisterUserCommand command) {
        if (userRepository.findByEmail(command.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setFullName(command.getFullName());
        user.setEmail(command.getEmail());
        user.setPhoneNumber(command.getPhoneNumber());
        user.setDateOfBirth(command.getDateOfBirth());
        user.setGender(command.getGender());
        user.setPasswordHash(passwordEncoder.encode(command.getPassword()));

        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        user.getRoles().add(userRole);

        User savedUser = userRepository.save(user);

        UserRegisteredEvent event = userMapper.toUserRegisteredEvent(savedUser);
        kafkaTemplate.send(TopicConstant.UserTopic.USER_REGISTERED, event);

        return savedUser;
    }
}
