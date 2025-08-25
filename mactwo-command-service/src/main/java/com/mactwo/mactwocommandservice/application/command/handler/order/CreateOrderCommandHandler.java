package com.mactwo.command.application.command.handler.order;

import com.mactwo.command.application.command.order.CreateOrderCommand;
import com.mactwo.command.domain.model.*;
import com.mactwo.command.domain.repository.*;
import com.mactwo.command.infrastructure.mapper.OrderEventMapper;
import com.mactwo.document.constant.ApplicationConstant;
import com.mactwo.document.constant.TopicConstant;
import com.mactwo.document.event.order.OrderPlacedEvent;
import com.mactwo.document.event.product.StockUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateOrderCommandHandler {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ProductVariantRepository variantRepository;
    private final OrderEventMapper orderEventMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public Order handle(CreateOrderCommand command) {
        User user = userRepository.findById(command.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Address address = addressRepository.findById(command.getAddressId())
                .orElseThrow(() -> new RuntimeException("Address not found"));

        Order order = new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(ApplicationConstant.OrderStatus.PENDING);

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        for (var itemDto : command.getItems()) {
            ProductVariant variant = variantRepository.findById(itemDto.getVariantId())
                    .orElseThrow(() -> new RuntimeException("Product variant not found"));

            if (variant.getStockQuantity() < itemDto.getQuantity()) {
                throw new RuntimeException("Not enough stock for product: " + variant.getProduct().getName());
            }

            variant.setStockQuantity(variant.getStockQuantity() - itemDto.getQuantity());
            variantRepository.save(variant);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setVariant(variant);
            orderItem.setQuantity(itemDto.getQuantity());
            orderItem.setPrice(variant.getPrice());
            orderItems.add(orderItem);

            totalAmount = totalAmount.add(variant.getPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity())));
        }

        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        OrderPlacedEvent orderEvent = orderEventMapper.toOrderPlacedEvent(savedOrder);
        kafkaTemplate.send(TopicConstant.OrderTopic.ORDER_PLACED, orderEvent);

        for (OrderItem item : savedOrder.getItems()) {
            var stockEvent = new StockUpdatedEvent(
                item.getVariant().getId(),
                item.getVariant().getStockQuantity(),
                -item.getQuantity()
            );
            kafkaTemplate.send(TopicConstant.ProductTopic.STOCK_UPDATED, stockEvent);
        }

        return savedOrder;
    }
}