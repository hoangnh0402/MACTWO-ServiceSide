package com.mactwo.mactwocommandservice.infrastructure.config;

import com.mactwo.contants.TopicConstant;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    private NewTopic createTopic(String name) {
        return TopicBuilder.name(name)
                .partitions(1)
                .replicas(1)
                .build();
    }

    // --- Product Topics ---
    @Bean
    public NewTopic productCreatedTopic() {
        return createTopic(TopicConstant.ProductTopic.PRODUCT_CREATED);
    }

    @Bean
    public NewTopic productUpdatedTopic() {
        return createTopic(TopicConstant.ProductTopic.PRODUCT_UPDATED);
    }

    @Bean
    public NewTopic productDeletedTopic() {
        return createTopic(TopicConstant.ProductTopic.PRODUCT_DELETED);
    }

    @Bean
    public NewTopic stockUpdatedTopic() {
        return createTopic(TopicConstant.ProductTopic.STOCK_UPDATED);
    }

    // --- Category Topics ---
    @Bean
    public NewTopic categoryCreatedTopic() {
        return createTopic(TopicConstant.CategoryTopic.CATEGORY_CREATED);
    }

    @Bean
    public NewTopic categoryUpdatedTopic() {
        return createTopic(TopicConstant.CategoryTopic.CATEGORY_UPDATED);
    }

    @Bean
    public NewTopic categoryDeletedTopic() {
        return createTopic(TopicConstant.CategoryTopic.CATEGORY_DELETED);
    }

    // --- Order Topics ---
    @Bean
    public NewTopic orderPlacedTopic() {
        return createTopic(TopicConstant.OrderTopic.ORDER_PLACED);
    }

    @Bean
    public NewTopic orderStatusUpdatedTopic() {
        return createTopic(TopicConstant.OrderTopic.ORDER_STATUS_UPDATED);
    }

    // --- User Topics ---
    @Bean
    public NewTopic userRegisteredTopic() {
        return createTopic(TopicConstant.UserTopic.USER_REGISTERED);
    }

    @Bean
    public NewTopic userProfileUpdatedTopic() {
        return createTopic(TopicConstant.UserTopic.USER_PROFILE_UPDATED);
    }

    @Bean
    public NewTopic userAddressChangedTopic() {
        return createTopic(TopicConstant.UserTopic.USER_ADDRESS_CHANGED);
    }

    // --- Promotion Topics ---
    @Bean
    public NewTopic promotionCreatedTopic() {
        return createTopic(TopicConstant.PromotionTopic.PROMOTION_CREATED);
    }

    @Bean
    public NewTopic promotionUpdatedTopic() {
        return createTopic(TopicConstant.PromotionTopic.PROMOTION_UPDATED);
    }

    @Bean
    public NewTopic promotionDeletedTopic() {
        return createTopic(TopicConstant.PromotionTopic.PROMOTION_DELETED);
    }
}