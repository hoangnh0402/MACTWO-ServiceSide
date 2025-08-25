package com.mactwo.command.application.command.handler.order;

import com.mactwo.command.application.command.order.CancelOrderCommand;
import com.mactwo.command.domain.model.Order;
import com.mactwo.command.domain.repository.OrderRepository;
import com.mactwo.document.constant.ApplicationConstant;
import com.mactwo.document.constant.TopicConstant;
import com.mactwo.document.event.order.OrderStatusUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CancelOrderCommandHandler {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public void handle(CancelOrderCommand command) {
        Order order = orderRepository.findById(command.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin && !order.getUser().getId().equals(command.getUserId())) {
            throw new SecurityException("User does not have permission to cancel this order");
        }
        
        String oldStatus = order.getStatus();
        order.setStatus(ApplicationConstant.OrderStatus.CANCELLED);
        orderRepository.save(order);

        var event = new OrderStatusUpdatedEvent(
            order.getId(),
            order.getUser().getId(),
            order.getStatus(),
            oldStatus
        );
        kafkaTemplate.send(TopicConstant.OrderTopic.ORDER_STATUS_UPDATED, event);
    }
}