package com.mactwo.command.application.command.handler.address;

import com.mactwo.command.application.command.address.AddAddressCommand;
import com.mactwo.command.domain.model.Address;
import com.mactwo.command.domain.model.User;
import com.mactwo.command.domain.repository.AddressRepository;
import com.mactwo.command.domain.repository.UserRepository;
import com.mactwo.command.infrastructure.mapper.AddressMapper;
import com.mactwo.document.constant.ApplicationConstant;
import com.mactwo.document.constant.TopicConstant;
import com.mactwo.document.event.address.AddressChangedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddAddressCommandHandler {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public Address handle(AddAddressCommand command) {
        User user = userRepository.findById(command.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Address address = addressMapper.toAddress(command.getRequest());
        address.setUser(user);

        Address savedAddress = addressRepository.save(address);

        var event = new AddressChangedEvent(
            user.getId(), savedAddress.getId(), savedAddress.getShippingAddress(),
            savedAddress.isDefault(), ApplicationConstant.EventType.CREATE
        );
        kafkaTemplate.send(TopicConstant.UserTopic.USER_ADDRESS_CHANGED, event);

        return savedAddress;
    }
}