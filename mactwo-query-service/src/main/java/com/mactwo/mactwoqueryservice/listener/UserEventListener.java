package com.mactwo.query.listener;

import com.mactwo.document.constant.TopicConstant;
import com.mactwo.document.event.user.*;
import com.mactwo.query.document.UserDocument;
import com.mactwo.query.repository.UserSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserEventListener {

    private final UserSearchRepository userSearchRepository;

    @KafkaListener(topics = TopicConstant.UserTopic.USER_CREATED, groupId = "query-service-group")
    public void handleUserCreated(UserCreatedEvent event) {
        log.info("Received UserCreatedEvent for user ID: {}", event.getUserId());
        UserDocument doc = new UserDocument();
        doc.setUserId(event.getUserId());
        doc.setFullName(event.getFullName());
        doc.setEmail(event.getEmail());
        doc.setActive(true);
        userSearchRepository.save(doc);
    }

    @KafkaListener(topics = TopicConstant.UserTopic.USER_INFO_UPDATED, groupId = "query-service-group")
    public void handleUserInfoUpdated(UserInfoUpdatedEvent event) {
        log.info("Received UserInfoUpdatedEvent for user ID: {}", event.getUserId());
        userSearchRepository.findById(event.getUserId()).ifPresent(doc -> {
            doc.setFullName(event.getFullName());
            doc.setPhoneNumber(event.getPhoneNumber());
            doc.setDateOfBirth(event.getDateOfBirth());
            userSearchRepository.save(doc);
        });
    }
    
}