package com.mactwo.mactwocommandservice.application.command.handler.product;

import com.mactwo.contants.TopicConstant;
import com.mactwo.event.StockUpdatedEvent;
import com.mactwo.mactwocommandservice.application.command.command.product.UpdateStockCommand;
import com.mactwo.mactwocommandservice.domain.model.ProductVariant;
import com.mactwo.mactwocommandservice.domain.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateStockCommandHandler {
    private final ProductVariantRepository variantRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public ProductVariant handle(UpdateStockCommand command) {
        ProductVariant variant = variantRepository.findById(command.getVariantId())
                .orElseThrow(() -> new RuntimeException("Product Variant not found"));

        int quantityChange = command.getNewStockQuantity() - variant.getStockQuantity();
        variant.setStockQuantity(command.getNewStockQuantity());
        ProductVariant savedVariant = variantRepository.save(variant);

        var event = new StockUpdatedEvent(
                savedVariant.getId(),
                savedVariant.getStockQuantity(),
                quantityChange
        );
        kafkaTemplate.send(TopicConstant.ProductTopic.STOCK_UPDATED, event);

        return savedVariant;
    }
}
