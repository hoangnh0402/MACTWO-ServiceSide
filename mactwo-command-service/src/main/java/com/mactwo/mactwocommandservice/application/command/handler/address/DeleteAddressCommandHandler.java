package com.mactwo.command.application.command.handler.address;

import com.mactwo.command.application.command.address.DeleteAddressCommand;
import com.mactwo.command.domain.model.Address;
import com.mactwo.command.domain.repository.AddressRepository;
import com.mactwo.document.constant.ApplicationConstant;
import com.mactwo.document.constant.TopicConstant;
import com.mactwo.document.event.address.AddressChangedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteAddressCommandHandler {
    private final AddressRepository addressRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public void handle(DeleteAddressCommand command) {
        Address address = addressRepository.findById(command.getAddressId())
                .orElseThrow(() -> new RuntimeException("Address not found"));

        if (!address.getUser().getId().equals(command.getUserId())) {
            throw new SecurityException("User does not have permission to delete this address");
        }

        addressRepository.deleteById(command.getAddressId());

        var event = new AddressChangedEvent(
            command.getUserId(), command.getAddressId(), null,
            false, ApplicationConstant.EventType.DELETE
        );
        kafkaTemplate.send(TopicConstant.UserTopic.USER_ADDRESS_CHANGED, event);
    }
}