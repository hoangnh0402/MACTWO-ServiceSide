package com.mactwo.command.application.command.handler.order;

import com.mactwo.command.application.command.order.UpdateOrderStatusCommand;
import com.mactwo.command.domain.model.Order;
import com.mactwo.command.domain.repository.OrderRepository;
import com.mactwo.document.constant.TopicConstant;
import com.mactwo.document.event.order.OrderStatusUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateOrderStatusCommandHandler {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public Order handle(UpdateOrderStatusCommand command) {
        Order order = orderRepository.findById(command.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        String oldStatus = order.getStatus();
        order.setStatus(command.getNewStatus());
        Order updatedOrder = orderRepository.save(order);

        var event = new OrderStatusUpdatedEvent(
            updatedOrder.getId(),
            updatedOrder.getUser().getId(),
            updatedOrder.getStatus(),
            oldStatus
        );
        kafkaTemplate.send(TopicConstant.OrderTopic.ORDER_STATUS_UPDATED, event);

        return updatedOrder;
    }
}