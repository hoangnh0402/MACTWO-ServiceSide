package com.mactwo.command.application.command.handler.address;

import com.mactwo.command.application.command.address.UpdateAddressCommand;
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
public class UpdateAddressCommandHandler {
    private final AddressRepository addressRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public Address handle(UpdateAddressCommand command) {
        Address address = addressRepository.findById(command.getAddressId())
                .orElseThrow(() -> new RuntimeException("Address not found"));

        // Kiểm tra quyền sở hữu
        if (!address.getUser().getId().equals(command.getUserId())) {
            throw new SecurityException("User does not have permission to update this address");
        }

        address.setShippingAddress(command.getRequest().getShippingAddress());
        address.setDefault(command.getRequest().isDefault());

        Address savedAddress = addressRepository.save(address);

        var event = new AddressChangedEvent(
            command.getUserId(), savedAddress.getId(), savedAddress.getShippingAddress(),
            savedAddress.isDefault(), ApplicationConstant.EventType.UPDATE
        );
        kafkaTemplate.send(TopicConstant.UserTopic.USER_ADDRESS_CHANGED, event);

        return savedAddress;
    }
}