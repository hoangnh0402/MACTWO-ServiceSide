package com.mactwo.command.application.command.handler.promotion;

import com.mactwo.command.application.command.promotion.UpdatePromotionCommand;
import com.mactwo.command.domain.model.Promotion;
import com.mactwo.command.domain.repository.PromotionRepository;
import com.mactwo.document.constant.TopicConstant;
import com.mactwo.document.event.promotion.PromotionUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdatePromotionCommandHandler {
    private final PromotionRepository promotionRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public Promotion handle(UpdatePromotionCommand command) {
        Promotion promotion = promotionRepository.findById(command.getPromotionId())
                .orElseThrow(() -> new RuntimeException("Promotion not found"));

        promotion.setPromotionName(command.getRequest().getPromotionName());
        promotion.setDescription(command.getRequest().getDescription());
        promotion.setDiscountAmount(command.getRequest().getDiscountAmount());
        promotion.setStartDate(command.getRequest().getStartDate());
        promotion.setEndDate(command.getRequest().getEndDate());
        
        Promotion savedPromotion = promotionRepository.save(promotion);

        var event = new PromotionUpdatedEvent(
            savedPromotion.getId(), savedPromotion.getPromotionName(),
            savedPromotion.getDiscountAmount(), savedPromotion.getStartDate(), savedPromotion.getEndDate()
        );
        kafkaTemplate.send(TopicConstant.PromotionTopic.PROMOTION_UPDATED, event);

        return savedPromotion;
    }
}