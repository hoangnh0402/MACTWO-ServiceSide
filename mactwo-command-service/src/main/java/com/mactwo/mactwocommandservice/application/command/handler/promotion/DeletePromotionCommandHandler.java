package com.mactwo.command.application.command.handler.promotion;

import com.mactwo.command.application.command.promotion.DeletePromotionCommand;
import com.mactwo.command.domain.repository.PromotionRepository;
import com.mactwo.document.constant.TopicConstant;
import com.mactwo.document.event.promotion.PromotionDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeletePromotionCommandHandler {
    private final PromotionRepository promotionRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public void handle(DeletePromotionCommand command) {
        if (!promotionRepository.findById(command.getPromotionId()).isPresent()) {
            throw new RuntimeException("Promotion not found");
        }
        promotionRepository.deleteById(command.getPromotionId());

        var event = new PromotionDeletedEvent(command.getPromotionId());
        kafkaTemplate.send(TopicConstant.PromotionTopic.PROMOTION_DELETED, event);
    }
}