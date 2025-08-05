package com.mactwo.mactwocommandservice.application.command.handler.product;

import com.mactwo.contants.TopicConstant;
import com.mactwo.event.ProductDeletedEvent;
import com.mactwo.mactwocommandservice.application.command.command.product.DeleteProductCommand;
import com.mactwo.mactwocommandservice.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteProductCommandHandler {
    private final ProductRepository productRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public void handle(DeleteProductCommand command) {
        if (!productRepository.findById(command.getProductId()).isPresent()) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(command.getProductId());

        var event = new ProductDeletedEvent(command.getProductId());
        kafkaTemplate.send(TopicConstant.ProductTopic.PRODUCT_DELETED, event);
    }
}
