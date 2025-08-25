package com.mactwo.command.application.command.handler.promotion;

import com.mactwo.command.application.command.promotion.CreatePromotionCommand;
import com.mactwo.command.domain.model.Promotion;
import com.mactwo.command.domain.repository.PromotionRepository;
import com.mactwo.command.infrastructure.mapper.PromotionMapper;
import com.mactwo.document.constant.TopicConstant;
import com.mactwo.document.event.promotion.PromotionCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreatePromotionCommandHandler {
    private final PromotionRepository promotionRepository;
    private final PromotionMapper promotionMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public Promotion handle(CreatePromotionCommand command) {
        Promotion promotion = promotionMapper.toPromotion(command.getRequest());
        Promotion savedPromotion = promotionRepository.save(promotion);

        var event = new PromotionCreatedEvent(
            savedPromotion.getId(), savedPromotion.getPromotionName(),
            savedPromotion.getDiscountAmount(), savedPromotion.getStartDate(), savedPromotion.getEndDate()
        );
        kafkaTemplate.send(TopicConstant.PromotionTopic.PROMOTION_CREATED, event);

        return savedPromotion;
    }
}